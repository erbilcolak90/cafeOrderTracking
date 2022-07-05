package com.kofteciyusuf.app.repositories;

import com.kofteciyusuf.app.entities.OrderProduct;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderProductRepository extends MongoRepository<OrderProduct,String> {
}
