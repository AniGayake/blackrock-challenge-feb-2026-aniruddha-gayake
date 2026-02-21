package com.blackrock.challenge.dto.response;

import com.blackrock.challenge.dto.Transaction;

public class ValidTransactionFilterResponse extends Transaction {
    private boolean inKPeriod;

    public boolean isInKPeriod() {
        return inKPeriod;
    }

    public void setInKPeriod(boolean inKPeriod) {
        this.inKPeriod = inKPeriod;
    }
}
