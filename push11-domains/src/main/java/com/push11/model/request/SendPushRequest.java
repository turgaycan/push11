package com.push11.model.request;

import java.util.List;
import java.util.Map;

public class SendPushRequest extends BaseRequestModel {

	private static final long serialVersionUID = -2838450680332157508L;
	List<String> userIdList;
	
	Map<String, String> content;
	
	public List<String> getUserIdList() {
		return userIdList;
	}

	public void setUserIdList(List<String> userIdList) {
		this.userIdList = userIdList;
	}

	public Map<String, String> getContent() {
		return content;
	}

	public void setContent(Map<String, String> content) {
		this.content = content;
	}


}
