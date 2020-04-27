package kafka.streams.interactive.query.services;

import org.mddarr.orders.event.dto.Event1;
import org.mddarr.orders.event.dto.Order;
import org.mddarr.products.ProductAvro;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class AvroProductProducer {
    @Autowired
    private KafkaTemplate<String, ProductAvro> kafkaTemplateEvent1;

    private static final Logger logger = LoggerFactory.getLogger(AvroProductProducer.class);

    public void sendProduct(ProductAvro product) {
        logger.info("Send product  {}", product);
        kafkaTemplateEvent1.send("products", product);
    }



}
