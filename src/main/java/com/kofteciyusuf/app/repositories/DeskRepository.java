package com.kofteciyusuf.app.repositories;

import com.kofteciyusuf.app.entities.Desk;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DeskRepository extends MongoRepository<Desk,String> {
}
