package org.mddarr.orderservice.controllers;


import org.mddarr.orderservice.resources.OrderBean;
import org.mddarr.products.Order;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class OrdersController {

    @PostMapping("/orders/")
    public String product(@RequestParam(value="products") List<Long> products, @RequestParam(value="quantities") List<Long> quantities,
        @RequestParam(value="customerID")  Long cid, @RequestParam(value="price") Long price)
    {
        return "The length is " + quantities.size();
    }

        // @RequestParam(value="quantites") List<Long> quantities , @RequestParam(value="cid") Long cid, @RequestParam(value="price") Long price) {
//        OrderBean order = new OrderBean(cid, products, quantities,"CREATED", price );
//        Order a;
//        return order;



}
