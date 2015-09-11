package com.push11.data.repository;

import com.push11.domain.Application;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.RepositoryDefinition;

public interface ApplicationRepository extends MongoRepository<Application, String> {

}
