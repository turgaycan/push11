package com.push11.controller;

import com.push11.domain.Company;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

@RunWith(MockitoJUnitRunner.class)
public class CompanyControllerTest {

    @InjectMocks
    private CompanyController controller;

    private MockHttpServletRequest request = new MockHttpServletRequest();
    private MockHttpServletResponse response = new MockHttpServletResponse();


    @Test
    public void shouldNotCreateNewCompanyIfPropertiesIsProper() {
        Company company = new Company("1", " ");
        controller.registerCompany(company, response);
//        verifyZeroInteractions(companyService);
    }
}