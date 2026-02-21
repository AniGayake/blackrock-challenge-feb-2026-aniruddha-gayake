package com.blackrock.challenge.dto.response;

import com.blackrock.challenge.dto.Transaction;

public class InvalidTransaction extends Transaction {
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
