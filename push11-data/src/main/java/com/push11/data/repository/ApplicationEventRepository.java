package com.push11.data.repository;

import com.push11.domain.document.Application;
import com.push11.domain.document.ApplicationEvent;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface ApplicationEventRepository extends MongoRepository<ApplicationEvent, String> {

    @Query("{application.id : ?0}")
    List<ApplicationEvent> getEventsFindByApp(Application app);

    @Query(value = "{'application.id' : ?0,'eventName' : ?0 }")
    List<ApplicationEvent> getEventsFindByAppAndEventName(Application app, String eventName);

}
