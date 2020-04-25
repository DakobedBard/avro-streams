package org.mddarr.orders.resources;

import org.mddarr.orders.event.AvroProducer;
import org.mddarr.products.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class OrderController {

    private final AvroProducer producer;

    @Autowired
    OrderController(AvroProducer producer) {
        this.producer = producer;
    }

    @PostMapping("/orders/")
    public Order product(@RequestParam(value="products") String products, @RequestParam(value="quantities") String quantities,
                         @RequestParam(value="customerID")  Long cid, @RequestParam(value="price") Long price)
    {
        UUID uuid =  UUID.randomUUID();
        Order order = new Order(uuid.toString(),cid,products,quantities);
        this.producer.sendOrder(order);
        return order;
    }

}
