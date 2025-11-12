package com.stepa7.bank.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String owner;

    @Column(nullable = false, length = 3)
    private String currency; // USD, EUR, RUB

    @Column(name = "amount_currency", nullable = false, precision = 19, scale = 4)
    private BigDecimal amountCurrency;

    @Column(name = "amount_rub", nullable = false, precision = 19, scale = 4)
    private BigDecimal amountRub;
}