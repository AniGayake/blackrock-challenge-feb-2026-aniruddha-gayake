package com.blackrock.challenge.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;

import java.time.LocalDateTime;

public class ExpensesRequest {

    @NotNull(message = "Date is required")
    @PastOrPresent(message = "Date cannot be in the future")
    @JsonFormat(shape = JsonFormat.Shape.STRING,
            pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime date;

    @NotNull(message = "Amount is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Amount must be greater than 0")
    @DecimalMax(value = "999999999.99", message = "Amount exceeds maximum allowed value")
    @Digits(integer = 9, fraction = 2, message = "Amount must have up to 9 integer digits and 2 decimal places")
    private Double amount;

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

    @Override
    public String toString() {
        return "Expenses{" +
                "date=" + date +
                ", amount=" + amount +
                '}';
    }
}
