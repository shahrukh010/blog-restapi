package com.code.main.exception;

public class ErrorDetails {

    private String HttpError;
    private String message;
    private String details;

    public ErrorDetails(String httpError, String message, String details) {
        HttpError = httpError;
        this.message = message;
        this.details = details;
    }

    public String getHttpError() {
        return HttpError;
    }

    public void setHttpError(String httpError) {
        HttpError = httpError;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
