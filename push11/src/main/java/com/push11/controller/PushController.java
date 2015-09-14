package com.push11.controller;

import com.push11.controller.base.BaseController;
import com.push11.domain.Action;
import com.push11.domain.User;
import com.push11.model.ActionType;
import com.push11.model.request.RequestNotifyPushModel;
import com.push11.model.request.SendPushRequest;
import com.push11.service.Push11ManagerService;
import com.push11.util.Push11EndpointPaths;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RestController
@RequestMapping(value = {Push11EndpointPaths.PUSH, Push11EndpointPaths.V_PUSH})
public class PushController extends BaseController<User> {

    public PushController() {
        super(User.class);
    }

    @Autowired
    private Push11ManagerService push11ManagerService;

    @RequestMapping(value = Push11EndpointPaths.NEW, method = RequestMethod.POST)
    @ResponseStatus(org.springframework.http.HttpStatus.NO_CONTENT)
    public void pushNotification(@RequestBody SendPushRequest sendPushRequest, HttpServletRequest request, HttpServletResponse response) {
        if (CollectionUtils.isEmpty(sendPushRequest.getUserIdList()) &&
                MapUtils.isEmpty(sendPushRequest.getContent())) {
            response.setStatus(HttpStatus.SC_BAD_REQUEST);
            return;
        }
        User user = getJSON("/v2.3/user/" + sendPushRequest.getUserIdList().get(0), User.class);

        final Map<String, Boolean> resultMap = push11ManagerService.push(user, sendPushRequest.getContent());
        Action action = new Action();
        action.setActionType(ActionType.PUSH);
        action.setContent(sendPushRequest.getContent());
        action.setUser(user);
        action.setSucceed(resultMap.get(user.getRegistrationId()));

        postJSON(Push11EndpointPaths.NEW_ACTION, action);
    }

}
