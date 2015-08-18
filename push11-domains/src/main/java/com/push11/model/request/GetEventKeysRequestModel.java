package com.push11.model.request;

import com.push11.domain.Application;

public class GetEventKeysRequestModel extends BaseRequestModel {

	private Application app;

	private String eventName;

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
}
