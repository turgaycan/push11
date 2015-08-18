package com.push11.domain;


import org.springframework.data.annotation.Reference;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "events")
public class Event extends AbstractDocument {

	@Reference
	private User user;

	@Field("event_name")
	private String eventName;
	
	@Field("app_event")
	private AppEvent appEvent;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
