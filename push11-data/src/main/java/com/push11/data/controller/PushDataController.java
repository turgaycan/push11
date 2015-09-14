package com.push11.data.controller;

import com.google.common.collect.Lists;
import com.push11.data.service.ActionService;
import com.push11.data.service.Push11ManagerService;
import com.push11.data.service.UserService;
import com.push11.domain.User;
import com.push11.model.request.SendPushRequest;
import com.push11.util.Push11EndpointPaths;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(value = {Push11EndpointPaths.PUSH, Push11EndpointPaths.V_PUSH})
public class PushDataController {

    @Autowired
    private Push11ManagerService push11ManagerService;

    @Autowired
    private UserService userService;

    @Autowired
    private ActionService actionService;


    @RequestMapping(value = Push11EndpointPaths.NEW, method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void pushNotification(@RequestBody SendPushRequest sendPushRequest) {
        User user = userService.findUserByRegistrationId(sendPushRequest.getUserIds().get(0));
        final Map<String, Boolean> resultMap = push11ManagerService.push(Lists.newArrayList(user.getRegistrationId()), sendPushRequest.getContent());
        actionService.saveActions(user, resultMap, sendPushRequest.getContent());
    }



}
