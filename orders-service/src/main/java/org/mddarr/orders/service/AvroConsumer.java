package org.mddarr.orders.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.mddarr.orders.Constants;
import org.mddarr.orders.event.dto.Event1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class AvroConsumer {

    private static final Logger logger = LoggerFactory.getLogger(AvroConsumer.class);
    @KafkaListener(topics = Constants.EVENT_1_TOPIC, groupId = "group_id")
    public void consume(ConsumerRecord<String,Event1> record) {
        logger.info(String.format("Consumed message -> %s", record.value()));
    }

    @KafkaListener(topics = "md.inventory.orders", groupId = "group_id")
    public void consumeOrders(ConsumerRecord<?, ?> record) {
//        logger.info(String.format("Consumed order message has a customer ID of  -> %s" ,order.getPurchaser()));
        logger.info(String.format("Consumed md message -> %s" ,record.value()));// record.value()));
    }

    @KafkaListener(topics = "dbserver.inventory.orders", groupId = "group_id")
    public void consumeCustomer(ConsumerRecord<?, ?> record) {
        logger.info(String.format("Consumed dbserver message -> %s" ,record.value()));// record.value()));
    }
}
