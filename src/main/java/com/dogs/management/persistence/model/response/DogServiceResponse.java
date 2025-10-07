package com.dogs.management.persistence.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serial;
import java.io.Serializable;

public class DogServiceResponse implements Serializable {

    @Serial
    private static final long serialVersionUID = 6904833474883710007L;

    @JsonProperty
    private String message;

    @JsonProperty
    private Boolean status = false;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

}
