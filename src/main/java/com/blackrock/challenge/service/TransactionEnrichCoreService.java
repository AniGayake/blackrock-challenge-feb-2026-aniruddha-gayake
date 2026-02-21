package com.blackrock.challenge.service;

import com.blackrock.challenge.dto.request.ExpensesRequest;
import com.blackrock.challenge.dto.response.EnrichedTransactionResponse;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionEnrichCoreService {

    public List<EnrichedTransactionResponse> enrichTransactions(@Valid final List<ExpensesRequest> expensesList) {
       return expensesList.stream()
                .map(
                        expense->{
                            EnrichedTransactionResponse enrichedTransactionResponse = new EnrichedTransactionResponse();
                            Double amount = expense.getAmount();
                            Double ceiling = Math.ceil((amount/100))*100;
                            enrichedTransactionResponse.setAmount(expense.getAmount());
                            enrichedTransactionResponse.setDate(expense.getDate());
                            enrichedTransactionResponse.setCeiling(ceiling);
                            enrichedTransactionResponse.setRemanent(ceiling-amount);
                            return enrichedTransactionResponse;
                        }
                ).collect(Collectors.toList());
    }
}
