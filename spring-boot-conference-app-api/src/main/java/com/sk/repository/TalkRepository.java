package com.sk.repository;

import com.sk.entity.Talk;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TalkRepository extends MongoRepository<Talk, String> {

}

