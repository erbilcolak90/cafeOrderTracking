package com.cafeordertracking.app.repositories;

import com.cafeordertracking.app.entities.OrderProduct;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderProductRepository extends MongoRepository<OrderProduct,String> {
}
