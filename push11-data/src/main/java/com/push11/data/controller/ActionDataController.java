package com.push11.data.controller;

import com.push11.data.service.ActionService;
import com.push11.domain.Action;
import com.push11.model.request.RequestNotifyPushModel;
import com.push11.util.Push11EndpointPaths;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = {Push11EndpointPaths.ACTION, Push11EndpointPaths.V_ACTION})
public class ActionDataController {

    @Autowired
    private ActionService actionService;

    @RequestMapping(value = Push11EndpointPaths.NEW, method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void newAction(@RequestBody Action action) {
        actionService.save(action);
    }

    @RequestMapping(value = Push11EndpointPaths.NOTIFY, method = RequestMethod.POST)
    @ResponseStatus(org.springframework.http.HttpStatus.NO_CONTENT)
    public Action getActionFindByActionAndRegId(@RequestBody RequestNotifyPushModel notifyPushModeld) {
        return actionService.getActionFindByActionAndRegId(notifyPushModeld.getActionGroupId(), notifyPushModeld.getRegistrationId());
    }
}
