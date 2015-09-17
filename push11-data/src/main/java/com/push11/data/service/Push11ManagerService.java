package com.push11.data.service;

import com.push11.manager.Push11Manager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class Push11ManagerService {

    @Autowired(required = false)
    private Push11Manager push11Manager;

    public Map<String, Boolean> push(Map<String, List<String>> registrationIds, Map<String, String> contentMap) {
        return push11Manager.pushAllPlatforms(registrationIds, contentMap);
    }

}
