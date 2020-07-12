package com.example.splashlogin.model;

public class SignUpResult {
    private boolean success;
    private Record record;
    private String message;

    public SignUpResult(boolean success, Record record, String message) {
        this.success = success;
        this.record = record;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Record getRecord() {
        return record;
    }

    public void setRecord(Record record) {
        this.record = record;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

