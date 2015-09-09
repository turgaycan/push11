package com.push11.data.service;

import com.push11.data.repository.AppEventRepository;
import com.push11.data.repository.AppTagRepository;
import com.push11.domain.AppEvent;
import com.push11.domain.AppTag;
import com.push11.domain.Application;
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
        List<AppTag> appTagList = new ArrayList<AppTag>();

        appTagList.addAll(appTagRepository.getTagsFindByApp(app));

        for (AppTag appTag : appTagList) {
            list.addAll(appTag.getTagList());
        }
        return list;
    }

    @Override
    public List<String> getAllEvents(Application app) {

        Set<String> eventNameSet = new HashSet<String>();
        List<AppEvent> appEventList = new ArrayList<AppEvent>();
        appEventList.addAll(appEventRepository.getEventsFindByApp(app));
        for (AppEvent appEvent : appEventList) {
            eventNameSet.add(appEvent.getEventName());
        }

        return new ArrayList<String>(eventNameSet);

    }

    @Override
    public List<String> getEventKeys(Application app, String eventName) {

        Set<String> eventKeySet = new HashSet<String>();
        List<AppEvent> appEventList = new ArrayList<AppEvent>();
        appEventList.addAll(appEventRepository.getEventsFindByAppAndEventName(app, eventName));
        for (AppEvent appEvent : appEventList) {
            eventKeySet.addAll(appEvent.getEventValues().keySet());
        }

        return new ArrayList<String>(eventKeySet);
    }

    @Override
    public List<String> getEventValuesForKey(Application app, String eventName, String eventKey) {

        Set<String> eventValueSet = new HashSet<String>();
        List<AppEvent> appEventList = new ArrayList<AppEvent>();
        appEventList.addAll(appEventRepository.getEventsFindByAppAndEventName(app, eventName));
        for (AppEvent appEvent : appEventList) {
            if (appEvent.getEventValues().containsKey(eventKey)) {
                eventValueSet.add(appEvent.getEventValues().get(eventKey));
            }
        }
        return new ArrayList<String>(eventValueSet);
    }

    @Override
    public Map<String, Boolean> sendPushToDeviceList(List<String> deviceList, Map<String, String> content) {
        return null;
    }


}