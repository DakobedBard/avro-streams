package org.mddarr.orders.controller;


import org.mddarr.orders.beans.OrderDTO;
import org.mddarr.orders.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/orders")
public class OrdersController {
    private static final Logger log = LoggerFactory.getLogger(UserResource.class);

    private final OrderService orderService;

    @Autowired
    OrdersController(OrderService service) {
        this.orderService = service;
    }


    @PostMapping("/post/")
    public String postOrder(@RequestParam(value="products") List<String> products, @RequestParam(value="quantities") List<Long> quantities,
                          @RequestParam(value="cid")  String cid, @RequestParam(value="price") Double price)
    {
        OrderDTO order = new OrderDTO(products, quantities, price, cid);
        this.orderService.createOrder(order);
        return "order";
    }

    @PostMapping("/delete/")
    public String deleteOrder(@RequestParam(value="id") String id)
    {
        this.orderService.deleteOrder(id);
        return "order";
    }

}
