package com.push11.data.service;

import com.push11.data.repository.EventRepository;
import com.push11.domain.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class EventService {

    @Autowired
    EventRepository repository;

    public List<Event> getEventsByApp(String applicationId) {
        return repository.getEventsFindByApp(applicationId);
    }

    public List<Event> getEventsFindByAppAndEventName(String applicationId, String eventName) {
        return repository.getEventsFindByAppAndEventName(applicationId, eventName);
    }

    public void saveEvent(Event event) {
        repository.save(event);
    }
}
