package com.push11.domain;


import org.springframework.data.annotation.Id;

import java.io.Serializable;

public class AbstractDocument implements Serializable{

    @Id
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
