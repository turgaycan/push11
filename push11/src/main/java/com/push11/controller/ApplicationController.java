package com.push11.controller;

import com.push11.domain.Application;
import com.push11.util.Push11EndpointPaths;
import com.push11.validator.ApplicationValidator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping(value = {Push11EndpointPaths.APPLICATION, Push11EndpointPaths.V_APPLICATION})
public class ApplicationController extends BaseController<Application> {

    public ApplicationController() {
        super(Application.class);
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(new ApplicationValidator());
    }

    @RequestMapping(value = Push11EndpointPaths.NEW, method = RequestMethod.POST)
    public void registerCompany(@Valid @RequestBody Application application, HttpServletRequest request, HttpServletResponse response, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            response.setStatus(HttpStatus.NON_AUTHORITATIVE_INFORMATION.value());
            return;
        }
        postDocument(request.getRequestURI(), application);
    }

    @RequestMapping(value = Push11EndpointPaths.ID, method = RequestMethod.GET)
    public
    @ResponseBody
    Application getCompanyById(@PathVariable String id, HttpServletRequest request, HttpServletResponse response) {
        if (StringUtils.isBlank(id)) {
            response.setStatus(HttpStatus.NON_AUTHORITATIVE_INFORMATION.value());
            return Application.newInstance();
        }
        return getDocument(request.getRequestURI());
    }
}
