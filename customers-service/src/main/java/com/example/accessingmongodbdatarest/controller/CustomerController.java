package com.example.accessingmongodbdatarest.controller;

import com.example.accessingmongodbdatarest.bean.CustomerDTO;
import com.example.accessingmongodbdatarest.port.CustomerApiInterface;
import com.example.accessingmongodbdatarest.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customers/")
public class CustomerController implements CustomerApiInterface {

    @Autowired
    CustomerService customerSerivce;

    @Override
    public void addCustomer(String fname, String lname, String email, String address, Long age) {
        CustomerDTO customerDTO = new CustomerDTO(fname, lname, email, address, age);
        customerSerivce.addCusomter(customerDTO);
    }
}
