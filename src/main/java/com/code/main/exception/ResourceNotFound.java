package com.code.main.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFound extends RuntimeException {

    private String resourceName;
    private String fieldName;
    private String valueName;

    public ResourceNotFound(String resourceName, String fieldName, String valueName) {
        super(String.format("% not found with %s: '%s'", resourceName, fieldName, valueName));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.valueName = valueName;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getValueName() {
        return valueName;
    }

    public void setValueName(String valueName) {
        this.valueName = valueName;
    }
}
