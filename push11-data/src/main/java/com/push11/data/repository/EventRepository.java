package com.push11.data.repository;

import com.push11.domain.Event;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.RepositoryDefinition;

public interface EventRepository extends MongoRepository<Event, String> {
}
