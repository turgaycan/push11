package com.push11.domain;


import org.springframework.data.annotation.Reference;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "event")
public class Event extends AbstractDocument {

	@Reference
	private User user;

	@Field("name")
	private String name;
	
	@Field("application_event")
	private ApplicationEvent applicationEvent;

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
