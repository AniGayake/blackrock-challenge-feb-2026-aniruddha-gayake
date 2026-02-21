package com.blackrock.challenge.dto.request;

import com.blackrock.challenge.dto.Transaction;

import java.util.List;

public class TransactionValidationRequest {
    private Double wage;
    List<Transaction> transactions;

    public Double getWage() {
        return wage;
    }

    public void setWage(Double wage) {
        this.wage = wage;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    @Override
    public String toString() {
        return "TransactionValidationRequest{" +
                "wage=" + wage +
                ", transactions=" + transactions +
                '}';
    }
}
