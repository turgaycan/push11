package com.push11.data.repository;

import com.push11.data.config.DefaultTest;
import com.push11.domain.document.Company;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static junit.framework.Assert.assertFalse;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

@Ignore
@DefaultTest
public class CompanyRepositoryTest{

    @Autowired
    private CompanyRepository repository;

    private Company company1, company2, company3, company4, company5, company6;

    @Before
    public void init() {
        prepareCompanyTestData();
    }

    @Test
    public void shouldFindByQueryName() {
        Company company = repository.findByQueryCompanyName("push12");
        assertThat(company.getName(), equalTo(company4.getName()));
        assertFalse(company.getName().equals(company3.getName()));
    }

    private void prepareCompanyTestData() {
        company1 = new Company("1", "push11");
        repository.save(company1);
        company2 = new Company("2", "push14");
        repository.save(company2);
        company3 = new Company("3", "push122");
        repository.save(company3);
        company4 = new Company("4", "push12");
        repository.save(company4);
        company5 = new Company("5", "push13");
        repository.save(company5);
        company6 = new Company("6", "push11");
        repository.save(company6);
    }

}