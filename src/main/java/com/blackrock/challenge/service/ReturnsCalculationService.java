package com.blackrock.challenge.service;

import com.blackrock.challenge.dto.Transaction;
import com.blackrock.challenge.dto.request.ReturnCalculationRequest;
import com.blackrock.challenge.dto.response.FinancialReturnsResponse;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReturnsCalculationService {


    public FinancialReturnsResponse calculateReturnsByDates( ReturnCalculationRequest request) {
        FinancialReturnsResponse response = new FinancialReturnsResponse();

        List<Transaction> transactions = request.getTransactions();
       Double totalTxnAmout = transactions
                .stream()
                        .filter(t->t.getAmount()>0)
                                .mapToDouble(t->t.getAmount())
                                        .sum();
       Double totalCeilingAmt = transactions
               .stream()
                       .filter(t->t.getAmount()>0)
                               .mapToDouble(t->t.getCeiling())
                                       .sum();


        response.setTotalTransactionAmount(totalTxnAmout);
        response.setTotalCeiling(totalCeilingAmt);
        response.setSavingsByDates(null);
        return response;

    }
}
