package com.example.accessingmongodbdatarest.services;

import com.example.accessingmongodbdatarest.bean.CustomerDTO;
import com.example.accessingmongodbdatarest.dao.CustomerRepository;
import com.example.accessingmongodbdatarest.entity.CustomerEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public void addCusomter(CustomerDTO customer){
        UUID uuid = UUID.randomUUID();
        CustomerEntity customerEntity = new CustomerEntity(uuid.toString(), customer.getFirstName(), customer.getLastName(), customer.getEmail(), customer.getAddress(), customer.getAge());
        customerRepository.save(customerEntity);

    }


}
