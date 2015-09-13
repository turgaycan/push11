package com.push11.model.request;

import com.push11.domain.Event;

public class ReqSaveEventRequestModel extends BaseRequestModel {

	private static final long serialVersionUID = -635509533097207181L;
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
