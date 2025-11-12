package com.stepa7.bank.service;

import java.math.BigDecimal;
import java.util.Map;

public interface CbrParserService {
    Map<String, BigDecimal> parse(String xml);
}
