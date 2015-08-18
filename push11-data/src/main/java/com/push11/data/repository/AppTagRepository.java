package com.push11.data.repository;

import com.push11.domain.AppTag;
import com.push11.domain.Application;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.RepositoryDefinition;

import java.util.List;

@RepositoryDefinition(domainClass = AppTag.class, idClass = String.class)
public interface AppTagRepository extends MongoRepository<AppTag, String> {

	@Query("{app : ?0}")
	public List<AppTag> getTagsFindByApp(Application app);

}
