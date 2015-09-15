package com.push11.data.controller;

import com.push11.data.service.ApplicationService;
import com.push11.domain.Application;
import com.push11.util.Push11EndpointPaths;
import com.push11.validator.ApplicationValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = {Push11EndpointPaths.APPLICATION, Push11EndpointPaths.V_APPLICATION})
public class ApplicationDataController {


    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(new ApplicationValidator());
    }

    @Autowired
    private ApplicationService applicationService;

    @RequestMapping(value = Push11EndpointPaths.NEW, method = RequestMethod.POST)
    public void newApplication(@RequestBody Application application) {
        applicationService.saveEntity(application);
    }

    @RequestMapping(value = Push11EndpointPaths.ID, method = RequestMethod.GET)
    public
    @ResponseBody
    Application getApplication(@PathVariable String id) {
        return applicationService.findById(id);
    }

}
