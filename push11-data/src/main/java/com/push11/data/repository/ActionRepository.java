package com.push11.data.repository;

import com.push11.domain.Action;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface ActionRepository extends MongoRepository<Action, String> {

    @Query(value = "{ 'actionGroupId': ?0 ,'user.r_id':?1} ")
    public Action getActionFindByActionAndRegId(String actionGroupId, String regId);
}
