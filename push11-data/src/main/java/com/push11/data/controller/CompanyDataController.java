package com.push11.data.controller;

import com.push11.data.service.CompanyService;
import com.push11.domain.Company;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RequestMapping(value = "/company")
@RestController
public class CompanyDataController {

        @Autowired
        public CompanyService companyService;

        @RequestMapping(value = "/new", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
        @ResponseStatus(value = HttpStatus.NO_CONTENT)
        public void registerCompany(@RequestBody Company company, HttpServletResponse response) {
            if (StringUtils.isBlank(company.getCompanyName())) {
                response.setStatus(HttpStatus.NON_AUTHORITATIVE_INFORMATION.value());
                return;
            }

            companyService.saveEntity(company);
        }
}
