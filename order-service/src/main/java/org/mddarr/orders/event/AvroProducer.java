package org.mddarr.orders.event;

import org.mddarr.orders.Constants;
import org.mddarr.products.Event1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class AvroProducer {

    @Autowired
    private KafkaTemplate<String, Event1> kafkaTemplateEvent1;

    private static final Logger logger = LoggerFactory.getLogger(AvroProducer.class);

    public void sendEvent1(Event1 event1) {
        logger.info("Send event 1 {}", event1);
        kafkaTemplateEvent1.send(Constants.EVENT_1_TOPIC, event1);
    }

}
