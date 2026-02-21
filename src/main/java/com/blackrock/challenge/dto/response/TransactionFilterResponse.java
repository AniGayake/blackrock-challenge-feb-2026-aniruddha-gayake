package com.blackrock.challenge.dto.response;

import java.util.List;

public class TransactionFilterResponse {
    private List<ValidTransactionFilterResponse> valid;
    private List<InvalidTransaction> invalid;

    public List<ValidTransactionFilterResponse> getValid() {
        return valid;
    }

    public void setValid(List<ValidTransactionFilterResponse> valid) {
        this.valid = valid;
    }

    public List<InvalidTransaction> getInvalid() {
        return invalid;
    }

    public void setInvalid(List<InvalidTransaction> invalid) {
        this.invalid = invalid;
    }
}
