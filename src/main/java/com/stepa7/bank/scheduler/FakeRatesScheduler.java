package com.stepa7.bank.scheduler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class FakeRatesScheduler {
    @Scheduled(cron = "0 0 1 * * *")
    public void fakeUpdate() {
        log.info("[FakeRatesScheduler] Pretend-updated currency rates");
    }
}