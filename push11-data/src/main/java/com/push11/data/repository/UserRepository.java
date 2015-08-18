package com.push11.data.repository;

import com.push11.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.RepositoryDefinition;

@RepositoryDefinition(domainClass = User.class, idClass = String.class)
public interface UserRepository extends MongoRepository<User, String> {

	@Query("{r_id : ?0}")
	public User findUserByRegId(String regId);
}
