package com.push11.data.repository;

import com.push11.domain.Action;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ActionRepository extends MongoRepository<Action, String> {
}
