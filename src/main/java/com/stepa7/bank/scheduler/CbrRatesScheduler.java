package com.stepa7.bank.scheduler;

import com.stepa7.bank.repository.CurrencyRateRepository;
import com.stepa7.bank.service.CbrParserService;
import com.stepa7.bank.service.client.CbrFeignClient;
import com.stepa7.bank.model.entity.CurrencyRate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@Component
@Slf4j
@RequiredArgsConstructor
public class CbrRatesScheduler {
    private final CbrFeignClient cbrFeignClient;
    private final CbrParserService parser;
    private final CurrencyRateRepository rateRepository;

    @Scheduled(cron = "0 0 1 * * *")
//    @Scheduled(fixedRate = 20000)
    public void updateFromCbr() {
        try {
            String date = DateTimeFormatter.ofPattern("dd/MM/yyyy").format(OffsetDateTime.now(ZoneOffset.UTC));
            String xml = cbrFeignClient.getDailyRates(date);
            Map<String, BigDecimal> map = parser.parse(xml);
            updateIfPresent(map, "USD");
            updateIfPresent(map, "EUR");
            log.info("[CbrRatesScheduler] Updated rates from CBR for {}", date);
        } catch (Exception e) {
            log.error("Failed to update rates from CBR", e);
        }
    }

    private void updateIfPresent(Map<String, BigDecimal> map, String code) {
        if (map.containsKey(code)) {
            BigDecimal newRate = map.get(code);
            CurrencyRate r = rateRepository.findByCode(code).orElse(CurrencyRate.builder().code(code).build());
            r.setRate(newRate);
            rateRepository.save(r);
            log.info("Updated {} -> {}", code, newRate);
        }
    }
}