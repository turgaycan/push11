package com.push11.domain;


import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.NotNull;

@Document(collection = "company")
public class Company extends AbstractDocument{

    @NotNull(message = "Company Id Filed should not be empty!")
    @Field("company_id")
    private String companyId;

    @NotNull(message = "Company Name Filed should not be empty!")
    @Field("company_name")
    private String companyName;

    public Company() {
    }

    public Company(String companyId, String companyName) {
        this.companyId = companyId;
        this.companyName = companyName;
    }

    public Company(String id, String companyId, String companyName) {
        setId(id);
        this.companyId = companyId;
        this.companyName = companyName;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
