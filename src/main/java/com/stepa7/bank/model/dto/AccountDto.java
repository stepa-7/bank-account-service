package com.stepa7.bank.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@AllArgsConstructor
@Builder
public class AccountDto {
    private UUID id;
    private String owner;
    private String currency; // USD, EUR, RUB
    private BigDecimal amountCurrency;
    private BigDecimal amountRub;
}
