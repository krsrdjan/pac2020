package com.sk.repository;

import com.sk.entity.Language;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LanguageRepository extends MongoRepository<Language, String> {

}

