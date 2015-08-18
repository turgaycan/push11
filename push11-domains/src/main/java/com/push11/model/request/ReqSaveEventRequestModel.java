package com.push11.model.request;

import com.push11.domain.Event;

public class ReqSaveEventRequestModel extends BaseRequestModel {

	private String pushId;
	private Event event;


	public String getPushId() {
		return pushId;
	}

	public void setPushId(String pushId) {
		this.pushId = pushId;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

}
