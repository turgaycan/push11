package com.push11.data.controller;

import com.push11.data.service.EventService;
import com.push11.data.service.UserService;
import com.push11.domain.Application;
import com.push11.domain.Event;
import com.push11.domain.User;
import com.push11.model.request.GetEventKeysRequestModel;
import com.push11.model.request.GetEventValuesForKeyRequestModel;
import com.push11.model.request.ReqSaveEventRequestModel;
import com.push11.model.request.SendPushRequest;
import com.push11.util.Push11EndpointPaths;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(value = {Push11EndpointPaths.EVENT, Push11EndpointPaths.V_EVENT})
public class EventController {

    @Autowired
    private EventService service;

    @Autowired
    private UserService userService;

    @RequestMapping(value = Push11EndpointPaths.ALL, method = RequestMethod.POST)
    public
    @ResponseBody
    List<Event> getAllEvents(@RequestBody Application app) {
        if (app == null) {
            return new ArrayList<>();
        }
        return service.getEventsByApp(app.getApplicationId());
    }

    @RequestMapping(value = Push11EndpointPaths.NAMES, method = RequestMethod.POST)
    public
    @ResponseBody
    List<String> getEventKeys(@RequestBody GetEventKeysRequestModel request) {
        Set<String> eventKeySet = new HashSet<>();
        List<Event> eventList = service.getEventsFindByAppAndEventName(request.getApp().getApplicationId(), request.getEventName());
        for (Event event : eventList) {
            eventKeySet.addAll(event.getEventValues().keySet());
        }
        return new ArrayList<>(eventKeySet);
    }

    @RequestMapping(value = Push11EndpointPaths.VALUES, method = RequestMethod.POST)
    public
    @ResponseBody
    List<String> getEventValuesForKey(@RequestBody GetEventValuesForKeyRequestModel request) {

        Set<String> eventValueSet = new HashSet<>();
        List<Event> eventList = new ArrayList<>();
        eventList.addAll(service.getEventsFindByAppAndEventName(request.getApp().getApplicationId(), request.getEventName()));
        for (Event event : eventList) {
            if (event.getEventValues().containsKey(request.getEventKey())) {
                eventValueSet.add(event.getEventValues().get(request.getEventKey()));
            }
        }
        return new ArrayList<>(eventValueSet);
    }

    @RequestMapping(value = Push11EndpointPaths.NEW, method = RequestMethod.POST)
    public
    @ResponseBody
    void newEvent(@RequestBody ReqSaveEventRequestModel reqEvent) {
        User user = userService.findUserByRegistrationId(reqEvent.getPushId());
        Event event = new Event();
        event.setUser(user);
        event.setCreateDate(new Date());
        event.setName(reqEvent.getEventName());
        event.setEventValues(reqEvent.getEventValues());
        service.saveEvent(event);
    }

}
