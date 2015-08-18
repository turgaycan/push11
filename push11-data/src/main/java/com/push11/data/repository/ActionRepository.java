package com.push11.data.repository;

import com.push11.domain.Action;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.RepositoryDefinition;

@RepositoryDefinition(domainClass = Action.class, idClass = String.class)
public interface ActionRepository extends MongoRepository<Action, String> {
}
