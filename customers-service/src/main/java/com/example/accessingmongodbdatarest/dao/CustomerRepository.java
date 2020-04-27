package com.example.accessingmongodbdatarest.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.example.accessingmongodbdatarest.entity.CustomerEntity;
public interface CustomerRepository extends MongoRepository<CustomerEntity, String> {
}
