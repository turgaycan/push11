package com.push11.data.service;

import com.push11.data.repository.ApplicationEventRepository;
import com.push11.domain.document.ApplicationEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppEventService {

    @Autowired
    private ApplicationEventRepository applicationEventRepository;

    public ApplicationEvent saveDocument(ApplicationEvent applicationEvent){
        return applicationEventRepository.save(applicationEvent);
    }

    public void delete(ApplicationEvent applicationEvent) {
        applicationEventRepository.delete(applicationEvent);
    }
}
