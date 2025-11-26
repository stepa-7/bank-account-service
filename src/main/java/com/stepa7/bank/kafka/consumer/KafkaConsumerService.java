package com.stepa7.bank.kafka.consumer;

import com.stepa7.bank.kafka.dto.TransactionEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KafkaConsumerService {

    @KafkaListener(topics = "${kafka.topic.transaction-log}", groupId = "${kafka.consumer.group-id}")
    public void listen(TransactionEvent event) {
        log.info("Received transaction event from Kafka: {}", event);
        // Proceed
        log.info("Successfully processed transaction event for Account ID: {}", event.getAccountId());
    }
}
