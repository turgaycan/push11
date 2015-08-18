package com.push11.model.request;

import java.util.List;
import java.util.Map;

public class SendPushRequest extends BaseRequestModel {
	
	List<String> deviceList;
	
	Map<String, String> content;
	
	public List<String> getDeviceList() {
		return deviceList;
	}

	public void setDeviceList(List<String> deviceList) {
		this.deviceList = deviceList;
	}

	public Map<String, String> getContent() {
		return content;
	}

	public void setContent(Map<String, String> content) {
		this.content = content;
	}


}
