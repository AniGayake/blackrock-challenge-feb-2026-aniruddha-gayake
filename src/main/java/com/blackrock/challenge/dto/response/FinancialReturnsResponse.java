package com.blackrock.challenge.dto.response;

import java.util.List;

public class FinancialReturnsResponse {
    private Double totalTransactionAmount;
    private Double totalCeiling;
    private List<SavingsByDate> savingsByDates;

    public Double getTotalTransactionAmount() {
        return totalTransactionAmount;
    }

    public void setTotalTransactionAmount(Double totalTransactionAmount) {
        this.totalTransactionAmount = totalTransactionAmount;
    }

    public Double getTotalCeiling() {
        return totalCeiling;
    }

    public void setTotalCeiling(Double totalCeiling) {
        this.totalCeiling = totalCeiling;
    }

    public List<SavingsByDate> getSavingsByDates() {
        return savingsByDates;
    }

    public void setSavingsByDates(List<SavingsByDate> savingsByDates) {
        this.savingsByDates = savingsByDates;
    }
}
