package com.papaxiong.support.model;

public class BusinessBaseException extends RuntimeException {

    private String code;


    public BusinessBaseException(String code, String message) {
        super(message);
        this.code = code;
    }


    public BusinessBaseException(String message) {
        super(message);
        this.code = "99999";
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
