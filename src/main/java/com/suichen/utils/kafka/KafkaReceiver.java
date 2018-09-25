package com.suichen.utils.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class KafkaReceiver {
    private static Logger log = LoggerFactory.getLogger(KafkaReceiver.class);

    @KafkaListener(topics = {"test"})
    public void listen(ConsumerRecord<?,?> record) {
        Optional<?> kafkaMessage = Optional.ofNullable(record.value());

        if (kafkaMessage.isPresent()) {
            Object message = kafkaMessage.get();

            log.info("+++++++++++++++++ record = {}", record);
            log.info("+++++++++++++++++ message = {}", message);
        }
    }
}
