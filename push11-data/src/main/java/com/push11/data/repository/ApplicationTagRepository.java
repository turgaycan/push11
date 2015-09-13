package com.push11.data.repository;

import com.push11.domain.ApplicationTag;
import com.push11.domain.Application;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface ApplicationTagRepository extends MongoRepository<ApplicationTag, String> {

	@Query("{application : ?0}")
	List<ApplicationTag> findApplicationTagsByApplication(Application app);

}
