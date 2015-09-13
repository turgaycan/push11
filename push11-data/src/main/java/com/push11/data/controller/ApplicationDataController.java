package com.push11.data.controller;

import com.push11.data.service.ApplicationService;
import com.push11.domain.Application;
import com.push11.util.Push11EndpointPaths;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = {Push11EndpointPaths.APPLICATION, Push11EndpointPaths.V_APPLICATION})
public class ApplicationDataController {

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
