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
@Table(name = "account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @Column(nullable = false)
    private String owner;

    @Column(nullable = false, length = 3)
    private String currency; // USD, EUR, RUB

    @Column(name = "amount_currency", nullable = false, precision = 12, scale = 4)
    private BigDecimal amountCurrency;

    @Column(name = "amount_rub", nullable = false, precision = 12, scale = 4)
    private BigDecimal amountRub;
}