//package org.mddarr.orders.service;
//
//import org.mddarr.orders.beans.OrderDTO;
//import org.mddarr.orders.dao.OrderRepository;
//import org.mddarr.orders.event.dto.Order;
//import org.mddarr.orders.port.OrderServicePublish;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//public class OrderService {
//
//    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);
//    @Autowired
//    private OrderRepository orderRepository;
//
//    @Autowired
//    private OrderServicePublish orderServicePublish;
////
////    public OrderDTO createOrder(OrderDTO orderDTO){
////        Order order =new Order();
////
////        order.setOrderStatus(OrderStatusType.WAITING);
////        order.setStatusDescription(OrderStatusType.WAITING.name());
////        order=orderRepository.save(order);
////        orderServicePublish.sendOrder(order);
////        logger.info("Order with id "+order.getId()+" sent to kitchen service");
////        OrderDTO res=dozer.map(order,OrderDTO.class);
////        return res;
////    }
////
////    public List<OrderDTO> getAll(){
////        List<OrderDTO> res=new ArrayList<>();
////        List <Order> orderList=orderRepository.findAll();
////        if(orderList!=null)
////        {
////            for(Order order:orderList)
////                res.add(dozer.map(order,OrderDTO.class));
////        }
////        return res;
////    }
////
////    public OrderDTO getById(String id){
////        OrderDTO res=null;
////        Optional<Order> order=orderRepository.findById(id);
////        if(order.isPresent())
////            res=dozer.map(order.get(),OrderDTO.class);
////        return res;
////    }
//
//}
