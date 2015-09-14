package com.push11.controller;

import com.push11.controller.base.BaseController;
import com.push11.domain.Application;
import com.push11.domain.User;
import com.push11.model.request.ReqUpdateMemberIdModel;
import com.push11.util.Push11EndpointPaths;
import com.push11.validator.ApplicationValidator;
import com.push11.validator.UserValidator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping(value = {Push11EndpointPaths.USER, Push11EndpointPaths.V_USER})
public class UserController extends BaseController<User> {

    public UserController() {
        super(User.class);
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(new UserValidator());
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(value = Push11EndpointPaths.NEW, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void registerUser(@Valid @RequestBody User user, HttpServletRequest request) {
        postJSON(request.getRequestURI(), user);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(value = Push11EndpointPaths.UPDATE, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateUser(@Valid @RequestBody ReqUpdateMemberIdModel reqUpdateMemberIdModel, HttpServletRequest request) {
        postJSON(request.getRequestURI(), reqUpdateMemberIdModel);
    }

    @RequestMapping(value = Push11EndpointPaths.ID, method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    User getUser(@PathVariable String id, HttpServletRequest request, HttpServletResponse response) {
        if(StringUtils.isBlank(id)){
            response.setStatus(org.apache.http.HttpStatus.SC_BAD_REQUEST);
            return User.newInstance();
        }
        return getJSON(request.getRequestURI());
    }


}
