package com.push11.data.repository;

import com.push11.domain.document.Application;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ApplicationRepository extends MongoRepository<Application, String> {

}
