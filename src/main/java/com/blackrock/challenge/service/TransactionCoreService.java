package com.blackrock.challenge.service;

import com.blackrock.challenge.dto.Transaction;
import com.blackrock.challenge.dto.request.ExpensesRequest;
import com.blackrock.challenge.dto.request.TransactionValidationRequest;
import com.blackrock.challenge.dto.response.InvalidTransaction;
import com.blackrock.challenge.dto.response.TransactionResponse;
import com.blackrock.challenge.dto.response.TransactionValidationResponse;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TransactionCoreService {

    public List<TransactionResponse> enrichTransactions(@Valid final List<ExpensesRequest> expensesList) {
       return expensesList.stream()
                .map(
                        expense->{
                            TransactionResponse enrichedTransactionResponse = new TransactionResponse();
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

    public TransactionValidationResponse validateTransactions(final TransactionValidationRequest transactionValidationRequest) {
        TransactionValidationResponse transactionValidationResponse = new TransactionValidationResponse();
        Set<Transaction> seen = new HashSet<>();
        Set<Transaction> invalidSeen= new HashSet<>();

        List<Transaction> transactionsList = transactionValidationRequest.getTransactions();

        List<InvalidTransaction> invalidTransactions =
                transactionsList.
                        stream()
                        .filter(t->t.getAmount()<0)
                        .map(t->{
                            InvalidTransaction invalidTransaction = new InvalidTransaction();
                            invalidTransaction.setAmount(t.getAmount());
                            invalidTransaction.setCeiling(t.getCeiling());
                            invalidTransaction.setDate(t.getDate());
                            invalidTransaction.setMessage("Negative Amounts not Allowed");
                            invalidSeen.add(t);
                            return invalidTransaction;
                        }).toList();


        List<Transaction> duplicateTransactions=
                transactionsList
                        .stream()
                        .filter(t->!invalidSeen.contains(t))
                        .filter(t->!seen.add(t))
                        .toList();

        List<Transaction> validTransactions = new ArrayList<>(seen);
        transactionValidationResponse.setValid(validTransactions);
        transactionValidationResponse.setDuplicates(duplicateTransactions);
        transactionValidationResponse.setInvalid(invalidTransactions);

        return transactionValidationResponse;
    }
}
