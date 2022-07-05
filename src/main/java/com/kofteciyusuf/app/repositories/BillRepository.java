package com.kofteciyusuf.app.repositories;

import com.kofteciyusuf.app.entities.Bill;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BillRepository extends MongoRepository<Bill,String> {
}
