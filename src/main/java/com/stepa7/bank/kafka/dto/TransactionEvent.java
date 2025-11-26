package com.stepa7.bank.kafka.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionEvent {
    private UUID accountId;
    private String type; // e.g., "CREATE", "UPDATE", "DELETE"
    private BigDecimal oldAmount;
    private BigDecimal newAmount;
    private String currency;
    private LocalDateTime timestamp;
    private String details;
}
