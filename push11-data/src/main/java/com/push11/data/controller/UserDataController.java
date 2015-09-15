package com.push11.data.controller;

import com.push11.data.service.UserService;
import com.push11.domain.User;
import com.push11.model.request.ReqUpdateMemberIdModel;
import com.push11.util.Push11EndpointPaths;
import com.push11.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;


@RestController
@RequestMapping(value = {Push11EndpointPaths.USER, Push11EndpointPaths.V_USER})
public class UserDataController {


    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(new UserValidator());
    }

    @Autowired
    private UserService userService;

    @RequestMapping(value = Push11EndpointPaths.NEW, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void registerUser(@RequestBody User user) {
        userService.saveEntity(user);
    }

    @RequestMapping(value = Push11EndpointPaths.UPDATE, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateUser(@Valid @RequestBody ReqUpdateMemberIdModel reqUpdateMemberIdModel, HttpServletRequest request) {
        userService.updateUser(reqUpdateMemberIdModel);
    }


    @RequestMapping(value = Push11EndpointPaths.ID, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public User getUser(@PathVariable String id) {
        return userService.findById(id);
    }
}
