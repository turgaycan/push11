package com.push11.data.repository;

import com.push11.domain.AppEvent;
import com.push11.domain.Application;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.RepositoryDefinition;

import java.util.List;

@RepositoryDefinition(domainClass = AppEvent.class, idClass = String.class)
public interface AppEventRepository extends MongoRepository<AppEvent, String> {

	@Query("{app.appId : ?0}")
	public List<AppEvent> getEventsFindByApp(Application app);

	@Query(value = "{ 'app.appId' : ?0,'eventName' : ?0 }")
	public List<AppEvent> getEventsFindByAppAndEventName(Application app, String eventName);

}
