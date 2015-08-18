package com.push11.data.service;

import com.push11.data.repository.AppEventRepository;
import com.push11.domain.AppEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppEventService {

    @Autowired
    private AppEventRepository appEventRepository;

    public AppEvent saveDocument(AppEvent appEvent){
        return appEventRepository.save(appEvent);
    }

    public void delete(AppEvent appEvent) {
        appEventRepository.delete(appEvent);
    }
}
