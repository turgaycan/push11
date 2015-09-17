package com.push11.domain.model.request;


import java.util.Map;

public class ReqSaveEventRequestModel extends BaseRequestModel {

    private static final long serialVersionUID = -635509533097207181L;
    private String pushId;
    private String actionGroupId;
    private String eventName;
    private Map<String, String> eventValues;


    public String getPushId() {
        return pushId;
    }

    public void setPushId(String pushId) {
        this.pushId = pushId;
    }

    public String getActionGroupId() {
        return actionGroupId;
    }

    public void setActionGroupId(String actionGroupId) {
        this.actionGroupId = actionGroupId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public Map<String, String> getEventValues() {
        return eventValues;
    }

    public void setEventValues(Map<String, String> eventValues) {
        this.eventValues = eventValues;
    }
}
