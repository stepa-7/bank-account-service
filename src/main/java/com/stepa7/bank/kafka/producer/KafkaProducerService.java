package com.stepa7.bank.kafka.producer;

import com.stepa7.bank.kafka.dto.TransactionEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaProducerService {

    @Value("${kafka.topic.transaction-log}")
    private String transactionLogTopic;

    private final KafkaTemplate<String, TransactionEvent> kafkaTemplate;

    public void sendTransactionEvent(TransactionEvent event) {
        log.info("Sending transaction event to Kafka: {}", event);
        kafkaTemplate.send(transactionLogTopic, event.getAccountId().toString(), event);
    }
}
