package com.push11.controller;

import com.push11.controller.base.BaseController;
import com.push11.model.base.BaseModel;
import com.push11.model.request.SendPushRequest;
import com.push11.util.Push11EndpointPaths;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(value = {Push11EndpointPaths.PUSH, Push11EndpointPaths.V_PUSH})
public class PushController {

}
