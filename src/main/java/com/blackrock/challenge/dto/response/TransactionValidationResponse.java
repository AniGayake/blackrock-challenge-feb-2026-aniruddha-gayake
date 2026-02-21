package com.blackrock.challenge.dto.response;


import com.blackrock.challenge.dto.Transaction;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransactionValidationResponse  {

    private List<Transaction> valid;
    private List<InvalidTransaction> invalid;
    private List<Transaction> duplicates;

    public List<Transaction> getValid() {
        return valid;
    }

    public void setValid(List<Transaction> valid) {
        this.valid = valid;
    }

    public List<InvalidTransaction> getInvalid() {
        return invalid;
    }

    public void setInvalid(List<InvalidTransaction> invalid) {
        this.invalid = invalid;
    }

    public List<Transaction> getDuplicates() {
        return duplicates;
    }

    public void setDuplicates(List<Transaction> duplicates) {
        this.duplicates = duplicates;
    }
}
