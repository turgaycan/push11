package com.push11.data.repository;

import com.push11.domain.ApplicationTag;
import com.push11.domain.Application;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.RepositoryDefinition;

import java.util.List;

@RepositoryDefinition(domainClass = ApplicationTag.class, idClass = String.class)
public interface AppTagRepository extends MongoRepository<ApplicationTag, String> {

	@Query("{app : ?0}")
	public List<ApplicationTag> getTagsFindByApp(Application app);

}
