package com.cafeordertracking.app.repositories;

import com.cafeordertracking.app.entities.Desk;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DeskRepository extends MongoRepository<Desk,String> {
}
