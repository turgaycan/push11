package com.push11.domain.model.request;

public class ReqUpdateMemberIdModel extends BaseRequestModel {

    private static final long serialVersionUID = -3617194966910333317L;
    private String registrationId;
    private String buyerId;

    public String getRegistrationId() {
        return registrationId;
    }

    public void setRegistrationId(String registrationId) {
        this.registrationId = registrationId;
    }

    public String getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }
}
