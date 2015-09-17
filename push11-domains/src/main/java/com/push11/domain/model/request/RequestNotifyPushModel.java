package com.push11.domain.model.request;

import com.push11.domain.model.base.BaseModel;

/**
 * Created by onurtaskin on 9/14/15.
 */
public class RequestNotifyPushModel extends BaseModel {

    private String registrationId;
    private String actionGroupId;


    public RequestNotifyPushModel(String registrationId, String actionGroupId) {
        this.registrationId = registrationId;
        this.actionGroupId = actionGroupId;
    }

    public String getActionGroupId() {
        return actionGroupId;
    }

    public void setActionGroupId(String actionGroupId) {
        this.actionGroupId = actionGroupId;
    }

    public String getRegistrationId() {
        return registrationId;
    }

    public void setRegistrationId(String registrationId) {
        this.registrationId = registrationId;
    }

}
