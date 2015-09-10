package com.push11.data.repository;

import com.push11.domain.ApplicationEvent;
import com.push11.domain.Application;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.RepositoryDefinition;

import java.util.List;

@RepositoryDefinition(domainClass = ApplicationEvent.class, idClass = String.class)
public interface AppEventRepository extends MongoRepository<ApplicationEvent, String> {

	@Query("{app.appId : ?0}")
	public List<ApplicationEvent> getEventsFindByApp(Application app);

	@Query(value = "{ 'app.appId' : ?0,'eventName' : ?0 }")
	public List<ApplicationEvent> getEventsFindByAppAndEventName(Application app, String eventName);

}
