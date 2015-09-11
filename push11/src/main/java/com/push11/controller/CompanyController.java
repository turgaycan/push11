package com.push11.controller;

import com.push11.domain.Company;
import com.push11.util.Push11EndpointPaths;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequestMapping(value = {Push11EndpointPaths.COMPANY, Push11EndpointPaths.V_COMPANY})
@RestController
public class CompanyController extends BaseController<Company> {

    public CompanyController() {
        super(Company.class);
    }

    @RequestMapping(value = Push11EndpointPaths.NEW, method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void registerCompany(@RequestBody Company company, HttpServletResponse response) {
        if (StringUtils.isBlank(company.getName())) {
            response.setStatus(HttpStatus.NON_AUTHORITATIVE_INFORMATION.value());
            return;
        }

        postJSON(Push11EndpointPaths.COMPANY_NEW, company, new String(""));
    }

    @RequestMapping(value = Push11EndpointPaths.ID, method = RequestMethod.GET)
    public
    @ResponseBody
    Company getCompanyById(@PathVariable String id, HttpServletRequest request, HttpServletResponse response) {
        if (StringUtils.isBlank(id)) {
            response.setStatus(HttpStatus.NON_AUTHORITATIVE_INFORMATION.value());
            return Company.newInstance();
        }
        Company company = getJSON(request.getRequestURI());

        return company;
    }
}
