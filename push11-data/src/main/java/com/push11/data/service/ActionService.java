package com.push11.data.service;

import com.push11.data.repository.ActionRepository;
import com.push11.domain.Action;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActionService {

    @Autowired
    private ActionRepository actionRepository;

    public void save(Action action){
        actionRepository.save(action);
    }

}
