package com.push11.data.controller;

import com.push11.data.service.ApplicationService;
import com.push11.domain.Application;
import com.push11.domain.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class ApplicationDataController {

    @Autowired
    private ApplicationService applicationService;

    @RequestMapping(value = "/application/user/new", method = RequestMethod.POST)
    public void newApplication(@RequestBody Application application){

        applicationService.saveEntity(application);
    }

    @RequestMapping(value = "/application/{id}", method = RequestMethod.GET)
    public @ResponseBody Application getApplication(@PathVariable String id){
        Application application = new Application();
        application.setId(UUID.randomUUID().toString());
        application.setAppId(id);
        application.setAppName("appname");
        application.setCompany(new Company(UUID.randomUUID().toString(), "id", "companyName"));
        return applicationService.saveEntity(application);
    }

}
