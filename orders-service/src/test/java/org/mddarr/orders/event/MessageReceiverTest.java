package org.mddarr.orders.event;

import org.mddarr.orders.UatAbstractTest;

import org.junit.Test;
import org.mddarr.orders.service.AvroConsumer;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Java6Assertions.assertThat;


public class MessageReceiverTest extends UatAbstractTest {

    @Autowired
    private AvroConsumer messageReceiver;

    @Test
    public void should_receive_event_3() {
//        event3Producer.send(new ProducerRecord<>(Constants.EVENT_3_TOPIC, null, Event3.newBuilder()
//                .setTimestamp(System.currentTimeMillis())
//                .setId(1)
//                .build()));
//
//        ConsumerRecord<String, Event3> consumerRecord = KafkaTestUtils.getSingleRecord(event3Consumer, Constants.EVENT_3_TOPIC);
//        assertThat(consumerRecord).isNotNull();
    }

    @Test
    public void should_receive_event_4() {
//        event4Producer.send(new ProducerRecord<>(Constants.EVENT_4_TOPIC, null, Event4.newBuilder()
//                .setTimestamp(System.currentTimeMillis())
//                .setId("id")
//                .build()));
//
//        ConsumerRecord<String, Event4> consumerRecord = KafkaTestUtils.getSingleRecord(event4Consumer, Constants.EVENT_4_TOPIC);
//        assertThat(consumerRecord).isNotNull();
    }

}