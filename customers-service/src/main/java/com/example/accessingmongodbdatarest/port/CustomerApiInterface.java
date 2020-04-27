package com.example.accessingmongodbdatarest.port;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

public interface CustomerApiInterface {

    @RequestMapping(value = "create", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    void addCustomer(@RequestParam("first_name") String fname, @RequestParam("first_name") String lname, @RequestParam("email") String email,
                     @RequestParam("address") String address,@RequestParam("age") Long age);


}