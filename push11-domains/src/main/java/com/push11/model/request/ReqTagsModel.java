package com.push11.model.request;


public class ReqTagsModel extends BaseRequestModel {

	private static final long serialVersionUID = -3145373763615641464L;
	private String pushId;
	private String[] tags;

	public String getPushId() {
		return pushId;
	}

	public void setPushId(String pushId) {
		this.pushId = pushId;
	}

	public String[] getTags() {
		return tags;
	}

	public void setTags(String[] tags) {
		this.tags = tags;
	}

}
