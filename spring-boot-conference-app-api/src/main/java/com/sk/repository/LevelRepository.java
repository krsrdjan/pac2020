package com.sk.repository;

import com.sk.entity.Level;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LevelRepository extends MongoRepository<Level, String> {

}

