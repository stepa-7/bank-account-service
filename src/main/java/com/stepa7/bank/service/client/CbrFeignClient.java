package com.stepa7.bank.service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "cbrClient", url = "https://cbr.ru")
public interface CbrFeignClient {
    @GetMapping("/scripts/XML_daily.asp")
    String getDailyRates(@RequestParam("date_req") String dateReq);
}