package com.push11.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.push11.domain.Company;
import com.push11.util.Push11EndpointPaths;
import org.apache.commons.lang3.StringUtils;

@RequestMapping(value = "/company")
@RestController
public class CompanyController extends BaseController<Company> {

    public CompanyController() {
        super(Company.class);
    }

    @RequestMapping(value = Push11EndpointPaths.NEW, method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void registerCompany(@RequestBody Company company, HttpServletResponse response) {
        if (StringUtils.isBlank(company.getCompanyName())) {
            response.setStatus(HttpStatus.NON_AUTHORITATIVE_INFORMATION.value());
            return;
        }

        postDocument("/company/new", company, new String(""));
    }
}
