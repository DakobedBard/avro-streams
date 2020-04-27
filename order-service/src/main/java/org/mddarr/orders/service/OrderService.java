package org.mddarr.orders.service;


import org.mddarr.orders.beans.OrderDTO;
import org.mddarr.orders.dao.OrderRepository;
import org.mddarr.orders.entity.OrderEntity;
import org.mddarr.orders.event.dto.Order;
import org.mddarr.orders.event.dto.OrderState;
import org.mddarr.orders.port.OrderServicePublish;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

//
@Service
public class OrderService {
    private static final Logger log = LoggerFactory.getLogger(OrderService.class);


    @Autowired
    OrderRepository orderRepositoryy;
    private final OrderAvroProducer producer;

    @Autowired
    OrderService(OrderAvroProducer producer, OrderRepository repository) {
        this.producer = producer; this.orderRepositoryy = repository;
    }

    @Autowired
    private OrderServicePublish orderServicePublish;
//
    public Order createOrder(OrderDTO orderDTO){
        UUID uuid = UUID.randomUUID();

        Order order =new Order(uuid.toString(), orderDTO.getCustomerID(), OrderState.PENDING, orderDTO.getProducts(), orderDTO.getQuantities(), orderDTO.getPrice());
//        order.setState(OrderState.PENDING);
//        order=orderRepository.save(order);
//        orderServicePublish.sendOrder(order);
        OrderEntity orderEntity = new OrderEntity(uuid.toString(),orderDTO.getProducts(),orderDTO.getQuantities(), orderDTO.getPrice(), orderDTO.getCustomerID(), OrderState.PENDING.toString());
        orderRepositoryy.save(orderEntity);
        producer.sendOrder(order);
        log.info("Order with id "+order.getId()+" sent to orders topic");
        return order;
    }

    public void deleteOrder(String id){

    }



//


}
