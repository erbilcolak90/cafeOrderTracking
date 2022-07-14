package com.cafeordertracking.app.repositories;

import com.cafeordertracking.app.entities.Product;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product,String> {

}
