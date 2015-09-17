package com.push11.domain.document;

import com.push11.domain.model.ActionType;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Reference;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;
import java.util.Map;

@Document(collection = "action")
public class Action extends AbstractDocument {

    private static final long serialVersionUID = 4902372770872634703L;

    @Field("action_id")
    @Id
    private String actionId;

    @Field("action_type")
    private ActionType actionType;

    @Field("create_date")
    private Date createDate = new Date();

    private boolean succeed;

    private boolean opened;

    private Map<String, String> content;

    @Reference
    private User user;

    private String actionGroupId;

    private Campaign campaign;

    public Action() {
    }

    public String getActionId() {
        return actionId;
    }

    public void setActionId(String actionId) {
        this.actionId = actionId;
    }

    public ActionType getActionType() {
        return actionType;
    }

    public void setActionType(ActionType actionType) {
        this.actionType = actionType;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public boolean isSucceed() {
        return succeed;
    }

    public void setSucceed(boolean succeed) {
        this.succeed = succeed;
    }

    public boolean isOpened() {
        return opened;
    }

    public void setOpened(boolean opened) {
        this.opened = opened;
    }

    public Map<String, String> getContent() {
        return content;
    }

    public void setContent(Map<String, String> content) {
        this.content = content;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getActionGroupId() {
        return actionGroupId;
    }

    public void setActionGroupId(String actionGroupId) {
        this.actionGroupId = actionGroupId;
    }

    public Campaign getCampaign() {
        return campaign;
    }

    public void setCampaign(Campaign campaign) {
        this.campaign = campaign;
    }
}
