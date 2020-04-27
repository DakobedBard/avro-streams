package org.mddarr.orders.dao;

import org.mddarr.orders.beans.OrderDTO;
import org.mddarr.orders.entity.OrderEntity;
import org.mddarr.orders.event.dto.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends CrudRepository<OrderEntity, String> {


}

