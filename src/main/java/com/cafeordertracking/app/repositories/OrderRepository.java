package com.cafeordertracking.app.repositories;

import com.cafeordertracking.app.entities.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepository extends MongoRepository<Order,String> {
}
