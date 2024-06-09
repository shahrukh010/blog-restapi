package com.code.main.payload;

import java.util.Date;

public class ErrorDetails {

    private Date timestamp;
    private String message;
    private String errodetails;

    public ErrorDetails(Date timestamp, String message, String errodetails) {
        this.timestamp = timestamp;
        this.message = message;
        this.errodetails = errodetails;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getErrodetails() {
        return errodetails;
    }

    public void setErrodetails(String errodetails) {
        this.errodetails = errodetails;
    }
}
