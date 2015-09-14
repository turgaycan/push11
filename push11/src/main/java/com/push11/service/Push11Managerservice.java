package com.push11.service;

import com.google.common.collect.Lists;
import com.push11.domain.User;
import com.push11.manager.Push11Manager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class Push11ManagerService {

    @Autowired
    private Push11Manager push11Manager;

    public Map<String, Boolean> push(User user, Map<String, String> contentMap) {
         return push11Manager.pushAndroid(Lists.newArrayList(user.getRegistrationId()), contentMap);
    }
}
