package com.push11.domain;


import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.NotNull;

@Document(collection = "company")
public class Company extends AbstractDocument{

    @NotNull(message = "Company Name Filed should not be empty!")
    @Field("name")
    private String name;

    public Company() {
    }

    public Company(String companyId, String name) {
        super.setId(companyId);
        this.name = name;
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
