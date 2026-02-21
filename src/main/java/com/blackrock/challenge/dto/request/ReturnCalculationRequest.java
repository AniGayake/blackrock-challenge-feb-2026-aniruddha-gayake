package com.blackrock.challenge.dto.request;

import com.blackrock.challenge.dto.Transaction;

import java.util.List;

public class ReturnCalculationRequest extends TransactionFilterRequest{
    private int age;
    private Double inflation;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Double getInflation() {
        return inflation;
    }

    public void setInflation(Double inflation) {
        this.inflation = inflation;
    }

    @Override
    public String toString() {
        return "ReturnCalculationRequest{" +
                "age=" + age +
                ", inflation=" + inflation +
                ", transactions=" + transactions +
                '}';
    }
}
