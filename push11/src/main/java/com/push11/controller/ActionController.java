package com.push11.controller;

import com.push11.controller.base.BaseController;
import com.push11.domain.Action;
import com.push11.model.request.RequestNotifyPushModel;
import com.push11.util.Push11EndpointPaths;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@RestController
@RequestMapping(value = {Push11EndpointPaths.ACTION, Push11EndpointPaths.V_ACTION})
public class ActionController extends BaseController<Action> {
    public ActionController() {
        super(Action.class);
    }

    @RequestMapping(value = Push11EndpointPaths.NOTIFY, method = RequestMethod.POST)
    @ResponseStatus(org.springframework.http.HttpStatus.NO_CONTENT)
    public void notifyPushResult(@RequestBody RequestNotifyPushModel notifyPushModel, HttpServletRequest request, HttpServletResponse response) {

        Action action = postJSON(request.getRequestURI(), notifyPushModel);
        action.setOpened(true);
        postJSON(Push11EndpointPaths.NEW_ACTION, action);
    }
}
