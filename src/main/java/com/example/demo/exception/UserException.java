package com.example.demo.exception;

public class UserException extends Exception {
    private String message;
    private int errorCode;

    public UserException(String message) {
        super();
        this.message=message;
    }
    public UserException(String message, int errorCode){
        super();
        this.message=message;
        this.errorCode=errorCode;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }
}
