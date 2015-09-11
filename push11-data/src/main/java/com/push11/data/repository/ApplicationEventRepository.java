package com.push11.data.repository;

import com.push11.domain.Application;
import com.push11.domain.ApplicationEvent;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface ApplicationEventRepository extends MongoRepository<ApplicationEvent, String> {

    @Query("{application.id : ?0}")
    public List<ApplicationEvent> getEventsFindByApp(Application app);

    @Query(value = "{'application.id' : ?0,'eventName' : ?0 }")
    public List<ApplicationEvent> getEventsFindByAppAndEventName(Application app, String eventName);

}
