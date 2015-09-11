package com.push11.domain;


import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Reference;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.NotNull;

@Document(collection = "event")
public class Event extends AbstractDocument {

	private static final long serialVersionUID = -148547925922649692L;

	@Id
	@NotNull(message = "Event Id Filed should not be empty!")
	@Field(value = "event_id")
	private String eventId;

	@Reference
	private User user;

	@Field("name")
	private String name;
	
	@Field("application_event")
	private ApplicationEvent applicationEvent;

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

	public ApplicationEvent getApplicationEvent() {
		return applicationEvent;
	}

	public void setApplicationEvent(ApplicationEvent applicationEvent) {
		this.applicationEvent = applicationEvent;
	}
}
