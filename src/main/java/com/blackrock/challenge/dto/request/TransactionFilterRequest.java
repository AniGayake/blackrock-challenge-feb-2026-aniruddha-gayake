package com.blackrock.challenge.dto.request;

import com.blackrock.challenge.dto.Transaction;

import java.util.List;

public class TransactionFilterRequest {
    private List<QPeriod> q;
    private List<PPeriod> p;
    private List<KPeriod> k;
    private Double wage;
    List<Transaction> transactions;

    public List<QPeriod> getQ() {
        return q;
    }

    public void setQ(List<QPeriod> q) {
        this.q = q;
    }

    public List<PPeriod> getP() {
        return p;
    }

    public void setP(List<PPeriod> p) {
        this.p = p;
    }

    public List<KPeriod> getK() {
        return k;
    }

    public void setK(List<KPeriod> k) {
        this.k = k;
    }

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
        return "TransactionFilterRequest{" +
                "q=" + q +
                ", p=" + p +
                ", k=" + k +
                ", wage=" + wage +
                ", transactions=" + transactions +
                '}';
    }
}
