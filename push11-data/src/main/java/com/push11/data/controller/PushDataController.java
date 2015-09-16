package com.push11.data.controller;

import com.google.common.collect.Lists;
import com.push11.data.service.ActionService;
import com.push11.data.service.Push11ManagerService;
import com.push11.data.service.UserService;
import com.push11.domain.Action;
import com.push11.domain.User;
import com.push11.manager.Push11Manager;
import com.push11.manager.Push11NotificationConstants;
import com.push11.manager.Push11NotificationManager;
import com.push11.model.ActionType;
import com.push11.model.request.SendPushRequest;
import com.push11.util.Push11EndpointPaths;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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
        List<User> userList = userService.findUsersListByBuyerIds(sendPushRequest.getUserIds());
        Map<String, List<String>> categorizedUserList = definePlatformTypeOfUsers(userList);
        Map<String, Boolean> pushResultMap = push11ManagerService.push(categorizedUserList, sendPushRequest.getContent());
        String actionGroupId = RandomStringUtils.randomAlphanumeric(32).toUpperCase();
        List<Action> actionList = new ArrayList<>();
        for (User user : userList) {
            Action action = new Action();
            action.setUser(user);
            action.setCreateDate(new Date());
            action.setSucceed(pushResultMap.get(user.getRegistrationId()));
            action.setActionType(ActionType.PUSH);
            action.setActionGroupId(actionGroupId);
            action.setCampaign(sendPushRequest.getCampaign());
            actionList.add(action);
        }
        actionService.saveActions(actionList);
    }


    private Map<String, List<String>> definePlatformTypeOfUsers(List<User> userList) {
        Map<String, List<String>> deviceMap = new HashMap<>();
        List<String> androidList = new ArrayList<>();
        List<String> iosList = new ArrayList<>();
        deviceMap.put(Push11NotificationConstants.PLATFORM_ANDROID, androidList);
        deviceMap.put(Push11NotificationConstants.PLATFORM_IOS, iosList);
        for (User user : userList) {
            if (Push11NotificationConstants.PLATFORM_ANDROID.equals(user.getOsType())) {
                androidList.add(user.getRegistrationId());
            } else if (Push11NotificationConstants.PLATFORM_IOS.equals(user.getOsType())) {
                iosList.add(user.getRegistrationId());
            }
        }
        return deviceMap;
    }
}
