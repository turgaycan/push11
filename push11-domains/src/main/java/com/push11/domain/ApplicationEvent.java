package com.push11.domain;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;


@Document(collection = "application_event")
public class ApplicationEvent extends AbstractDocument{

	private Application application;
	
	private Map<String, String> eventValues;
	
	private String eventName;

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
