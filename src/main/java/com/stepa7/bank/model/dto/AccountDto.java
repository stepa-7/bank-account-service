package com.stepa7.bank.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@Builder
public class AccountDto {
    private String owner;
    private String currency; // USD, EUR, RUB
    private BigDecimal amountCurrency;
    private BigDecimal amountRub;
}
