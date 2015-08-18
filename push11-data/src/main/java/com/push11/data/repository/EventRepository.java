package com.push11.data.repository;

import com.push11.domain.Event;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.RepositoryDefinition;

@RepositoryDefinition(domainClass = Event.class, idClass = String.class)
public interface EventRepository extends MongoRepository<Event, String> {
}
