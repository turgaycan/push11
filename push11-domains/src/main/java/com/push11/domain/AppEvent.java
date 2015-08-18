package com.push11.domain;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;


@Document(collection = "appEvents")
public class AppEvent extends AbstractDocument{

	private Application app;
	
	private Map<String, String> eventValues;
	
	private String eventName;

	public Application getApp() {
		return app;
	}

	public void setApp(Application app) {
		this.app = app;
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
