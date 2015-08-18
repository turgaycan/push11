package com.push11.model.request;

import com.push11.domain.Application;

public class GetEventValuesForKeyRequestModel extends BaseRequestModel {

	Application app;

	String eventName;

	String eventKey;

	public Application getApp() {
		return app;
	}

	public void setApp(Application app) {
		this.app = app;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public String getEventKey() {
		return eventKey;
	}

	public void setEventKey(String eventKey) {
		this.eventKey = eventKey;
	}

}
