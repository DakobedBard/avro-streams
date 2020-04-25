package org.mddarr.orders;

import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import javax.annotation.PostConstruct;

@SpringBootApplication
@ComponentScan(basePackages = "org.mddarr.orders")

public class OrdersServiceApplication {

    @Value("${topic.name}")
    private String topicName;

    @Value("${topic.partitions-num}")
    private Integer partitions;

    @Value("${topic.replication-factor}")
    private short replicationFactor;


    public static void main(String[] args) {
        SpringApplication.run(OrdersServiceApplication.class, args);
    }

    private static final Logger logger = LoggerFactory.getLogger(OrdersServiceApplication.class);

    @PostConstruct
    public void postInit() {
        logger.info("Application ShowcaseApp started!");
    }

    @Bean
    NewTopic moviesTopic() {
        return new NewTopic(topicName, partitions, replicationFactor);
    }

}
