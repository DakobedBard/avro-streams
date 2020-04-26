package org.mddarr.orders.dao;

import org.mddarr.orders.beans.OrderDTO;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends MongoRepository<OrderDTO, String> {


}

