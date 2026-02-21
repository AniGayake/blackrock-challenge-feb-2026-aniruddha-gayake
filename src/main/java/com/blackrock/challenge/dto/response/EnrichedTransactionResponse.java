package com.blackrock.challenge.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class EnrichedTransactionResponse {

    @JsonFormat(shape = JsonFormat.Shape.STRING,
            pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime date;
    private Double amount;
    private Double ceiling;
    private Double remanent;

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getCeiling() {
        return ceiling;
    }

    public void setCeiling(Double ceiling) {
        this.ceiling = ceiling;
    }

    public Double getRemanent() {
        return remanent;
    }

    public void setRemanent(Double remanent) {
        this.remanent = remanent;
    }

    @Override
    public String toString() {
        return "EnrichedTransactionResponse{" +
                "date=" + date +
                ", amount=" + amount +
                ", ceiling=" + ceiling +
                ", remanent=" + remanent +
                '}';
    }
}
