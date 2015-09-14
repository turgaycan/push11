package com.push11.data.repository;

import com.push11.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;


public interface UserRepository extends MongoRepository<User, String> {

	@Query("{registration_id : ?0}")
	User findUserByRegistrationId(String registrationId);

}
