package com.push11.data.service;

import com.push11.domain.ApplicationEvent;
import com.push11.domain.ApplicationTag;
import com.push11.domain.Application;
import com.push11.data.repository.AppEventRepository;
import com.push11.data.repository.AppTagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class WebApiServiceImpl implements WebApiService{

    @Autowired
    private AppTagRepository appTagRepository;
    @Autowired
    private AppEventRepository appEventRepository;

    @Override
    public List<String> getAllTags(Application app) {

        List<String> list = new ArrayList<String>();
        List<ApplicationTag> applicationTagList = new ArrayList<ApplicationTag>();

        applicationTagList.addAll(appTagRepository.getTagsFindByApp(app));

        for (ApplicationTag applicationTag : applicationTagList) {
            list.addAll(applicationTag.getTagList());
        }
        return list;
    }

    @Override
    public List<String> getAllEvents(Application app) {

        Set<String> eventNameSet = new HashSet<String>();
        List<ApplicationEvent> applicationEventList = new ArrayList<ApplicationEvent>();
        applicationEventList.addAll(appEventRepository.getEventsFindByApp(app));
        for (ApplicationEvent applicationEvent : applicationEventList) {
            eventNameSet.add(applicationEvent.getEventName());
        }

        return new ArrayList<String>(eventNameSet);

    }

    @Override
    public List<String> getEventKeys(Application app, String eventName) {

        Set<String> eventKeySet = new HashSet<String>();
        List<ApplicationEvent> applicationEventList = new ArrayList<ApplicationEvent>();
        applicationEventList.addAll(appEventRepository.getEventsFindByAppAndEventName(app, eventName));
        for (ApplicationEvent applicationEvent : applicationEventList) {
            eventKeySet.addAll(applicationEvent.getEventValues().keySet());
        }

        return new ArrayList<String>(eventKeySet);
    }

    @Override
    public List<String> getEventValuesForKey(Application app, String eventName, String eventKey) {

        Set<String> eventValueSet = new HashSet<String>();
        List<ApplicationEvent> applicationEventList = new ArrayList<ApplicationEvent>();
        applicationEventList.addAll(appEventRepository.getEventsFindByAppAndEventName(app, eventName));
        for (ApplicationEvent applicationEvent : applicationEventList) {
            if (applicationEvent.getEventValues().containsKey(eventKey)) {
                eventValueSet.add(applicationEvent.getEventValues().get(eventKey));
            }
        }
        return new ArrayList<String>(eventValueSet);
    }

    @Override
    public Map<String, Boolean> sendPushToDeviceList(List<String> deviceList, Map<String, String> content) {
        return null;
    }


}