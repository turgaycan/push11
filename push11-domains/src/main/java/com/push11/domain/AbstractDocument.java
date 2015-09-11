package com.push11.domain;


import org.springframework.validation.annotation.Validated;

import java.io.Serializable;

@Validated
public abstract class AbstractDocument implements Serializable {

    private boolean deleted;

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}
