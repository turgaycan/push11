package com.push11.domain;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Map;


@Document(collection = "application_event")
public class ApplicationEvent extends AbstractDocument {

    private static final long serialVersionUID = 2197296246923069716L;

    private Application application;

    private Map<String, String> eventValues;

    @Field(value = "event_name")
    private String eventName;

    public ApplicationEvent() {
    }

    public Application getApplication() {
        return application;
    }

    public void setApplication(Application application) {
        this.application = application;
    }

    public Map<String, String> getEventValues() {
        return eventValues;
    }

    public void setEventValues(Map<String, String> eventValues) {
        this.eventValues = eventValues;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

}
