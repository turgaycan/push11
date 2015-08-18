package com.push11.model.request;

public class ReqUpdateMemberIdModel extends BaseRequestModel {

	private String pushId, memberId;

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getPushId() {
		return pushId;
	}

	public void setPushId(String pushId) {
		this.pushId = pushId;
	}

}
