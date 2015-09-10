package com.push11.service;

import com.push11.manager.Push11Manager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class Push11ManagerService {

    @Autowired
    private Push11Manager push11Manager;

    public Map<String, Boolean> pushAndroid(List<String> deviceList, Map<String, String> content) {
        return push11Manager.pushAndroid(deviceList, content);
    }

    public Map<String, Boolean> pushIOS(List<String> deviceList, Map<String, String> content) {
        return push11Manager.pushIOS(deviceList, content);
    }

    public void setPush11Manager(Push11Manager push11Manager) {
        this.push11Manager = push11Manager;
    }

}
