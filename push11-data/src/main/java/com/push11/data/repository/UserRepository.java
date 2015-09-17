package com.push11.data.repository;

import com.push11.domain.document.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;


public interface UserRepository extends MongoRepository<User, String> {

    @Query("{registration_id : ?0}")
    User findUserByRegistrationId(String registrationId);

    @Query("{r_id : {$in:?0}}")
    public List<User> findUsersByBuyerId(Iterable<String> userIdList);

    @Query("{'app._id' : ?0}")
    public List<User> getUsersFindByApp(String appId);

}
