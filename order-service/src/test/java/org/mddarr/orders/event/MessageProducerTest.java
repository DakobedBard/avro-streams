package org.mddarr.orders.event;

import org.mddarr.orders.Constants;
import org.mddarr.orders.UatAbstractTest;
import org.mddarr.products.Event1;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.test.utils.KafkaTestUtils;

import static org.assertj.core.api.Assertions.assertThat;

public class MessageProducerTest extends UatAbstractTest {

    @Autowired
    private AvroProducer messageProducer;

    @Test
    public void should_send_event1() {
        messageProducer.sendEvent1(new Event1("name1", "value3"));

        ConsumerRecord<String, Event1> singleRecord = KafkaTestUtils.getSingleRecord(event1Consumer, Constants.EVENT_1_TOPIC);
        assertThat(singleRecord).isNotNull();
    }

}