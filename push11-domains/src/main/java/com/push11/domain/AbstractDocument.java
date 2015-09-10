package com.push11.domain;


import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;

public class AbstractDocument implements Serializable{

    @NotEmpty(message = "Id Filed should not be empty!")
    @Field("id")
    @Id
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
