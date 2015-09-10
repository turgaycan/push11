package com.push11.data.controller;

import com.push11.data.service.CompanyService;
import com.push11.domain.Company;
import com.push11.util.Push11EndpointPaths;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RequestMapping(value = Push11EndpointPaths.COMPANY)
@RestController
public class CompanyDataController {

        @Autowired
        public CompanyService companyService;

        @RequestMapping(value = Push11EndpointPaths.NEW, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
        @ResponseStatus(value = HttpStatus.NO_CONTENT)
        public void registerCompany(@RequestBody Company company, HttpServletResponse response) {
            if (StringUtils.isBlank(company.getName())) {
                response.setStatus(HttpStatus.NON_AUTHORITATIVE_INFORMATION.value());
                return;
            }

            companyService.saveEntity(company);
        }
}
