package com.blackrock.challenge.dto.response;

import java.time.LocalDateTime;

public class SavingsByDate {
    private LocalDateTime start;
    private LocalDateTime end;
    private Double amount;
    private Double profit;
    private Double taxBenefit;

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getProfit() {
        return profit;
    }

    public void setProfit(Double profit) {
        this.profit = profit;
    }

    public Double getTaxBenefit() {
        return taxBenefit;
    }

    public void setTaxBenefit(Double taxBenefit) {
        this.taxBenefit = taxBenefit;
    }
}
