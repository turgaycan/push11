package com.push11.model.request;

import java.util.List;
import java.util.Map;

public class SendPushRequest extends BaseRequestModel {

    private static final long serialVersionUID = -2838450680332157508L;
    List<String> userIds;

    Map<String, String> content;

    public List<String> getUserIds() {
        return userIds;
    }

    public void setUserIds(List<String> userIds) {
        this.userIds = userIds;
    }

    public Map<String, String> getContent() {
        return content;
    }

    public void setContent(Map<String, String> content) {
        this.content = content;
    }


}
