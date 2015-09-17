package com.push11.domain.document;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.NotNull;

@Document(collection = "company")
public class Company extends AbstractDocument{

    private static final long serialVersionUID = -4105664450218754414L;

    @Id
    @Field(value = "company_id")
    private String companyId;

    @NotNull(message = "Company Name Filed should not be empty!")
    @Field("name")
    private String name;

    public Company() {
    }

    public Company(String companyId, String name) {
        this.companyId = companyId;
        this.name = name;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static Company newInstance() {
        return new Company();
    }
}
