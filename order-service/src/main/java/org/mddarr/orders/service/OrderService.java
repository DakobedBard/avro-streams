package org.mddarr.orders.service;


import org.mddarr.orders.beans.OrderDTO;
import org.mddarr.orders.dao.OrderRepository;
import org.mddarr.orders.event.dto.Order;
import org.mddarr.orders.event.dto.OrderState;
import org.mddarr.orders.port.OrderServicePublish;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
//
public class OrderService {

    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderServicePublish orderServicePublish;
//
    public Order createOrder(OrderDTO orderDTO){
        Order order =new Order();
        order.setState(OrderState.CREATED);
        order=orderRepository.save(order);
        orderServicePublish.sendOrder(orderDTO);
        logger.info("Order with id "+order.getId()+" sent to kitchen service");

        return order;
    }

//


}
