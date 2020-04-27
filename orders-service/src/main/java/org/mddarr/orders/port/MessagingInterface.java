package org.mddarr.orders.port;

import org.springframework.kafka.annotation.KafkaListener;

public interface MessagingInterface {

    String TOPIC_ORDER_CALLBACK = "orderservicecallback";
    @KafkaListener(topics = TOPIC_ORDER_CALLBACK)
    void callback(String message);

}


