package com.push11.data.controller;

import com.push11.data.service.CompanyService;
import com.push11.domain.Company;
import com.push11.util.Push11EndpointPaths;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = {Push11EndpointPaths.COMPANY, Push11EndpointPaths.V_COMPANY})
public class CompanyDataController {

    @Autowired
    public CompanyService companyService;

    @RequestMapping(value = Push11EndpointPaths.NEW, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void registerCompany(@RequestBody Company company) {
        companyService.saveEntity(company);
    }

    @RequestMapping(value = Push11EndpointPaths.ID, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Company getCompany(@PathVariable String id) {
        return companyService.findById(id);
    }
}
