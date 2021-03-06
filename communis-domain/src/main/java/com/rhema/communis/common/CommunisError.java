package com.rhema.communis.common;


import org.springframework.http.HttpStatus;

import java.util.List;

public class CommunisError {
    private HttpStatus status;
    private Object message;
    private List<String> errors;

    public CommunisError(Object message) {
        this.message = message;
    }

    public CommunisError(HttpStatus status, Object message, List<String> errors) {
        this.status = status;
        this.message = message;
        this.errors = errors;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }
}
