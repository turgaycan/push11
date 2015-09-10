package com.push11.data.service;

import com.push11.data.repository.AppEventRepository;
import com.push11.domain.ApplicationEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppEventService {

    @Autowired
    private AppEventRepository appEventRepository;

    public ApplicationEvent saveDocument(ApplicationEvent applicationEvent){
        return appEventRepository.save(applicationEvent);
    }

    public void delete(ApplicationEvent applicationEvent) {
        appEventRepository.delete(applicationEvent);
    }
}
