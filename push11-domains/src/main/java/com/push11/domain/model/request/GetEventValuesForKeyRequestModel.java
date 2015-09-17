package com.push11.domain.model.request;

import com.push11.domain.document.Application;

public class GetEventValuesForKeyRequestModel extends BaseRequestModel {

	private static final long serialVersionUID = 5405665664128789536L;
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
