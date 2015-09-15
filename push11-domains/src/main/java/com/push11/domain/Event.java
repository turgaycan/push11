package com.push11.domain;


import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Reference;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Map;

@Document(collection = "event")
public class Event extends AbstractDocument {

    private static final long serialVersionUID = -148547925922649692L;

    @Id
    @Field(value = "event_id")
    private String eventId;

    @Reference
    private User user;

    @Field("name")
    private String name;

    @Field("c_date")
    private Date createDate;

    private Map<String, String> eventValues;


    public Event() {
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, String> getEventValues() {
        return eventValues;
    }

    public void setEventValues(Map<String, String> eventValues) {
        this.eventValues = eventValues;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
