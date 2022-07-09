package com.kofteciyusuf.app.repositories;

import com.kofteciyusuf.app.entities.Product;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ProductRepository extends MongoRepository<Product,String> {

}
