package com.blackrock.challenge.service;

import com.blackrock.challenge.dto.Transaction;
import com.blackrock.challenge.dto.request.ExpensesRequest;
import com.blackrock.challenge.dto.request.KPeriod;
import com.blackrock.challenge.dto.request.TransactionFilterRequest;
import com.blackrock.challenge.dto.request.TransactionValidationRequest;
import com.blackrock.challenge.dto.response.*;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
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

        List<InvalidTransaction> invalidTransactions = getInvalidTransactions(transactionsList, invalidSeen);

        List<Transaction> duplicateTransactions = getDuplicateTransactions(transactionsList, invalidSeen, seen);

        List<Transaction> validTransactions = new ArrayList<>(seen);
        transactionValidationResponse.setValid(validTransactions);
        transactionValidationResponse.setDuplicates(duplicateTransactions);
        transactionValidationResponse.setInvalid(invalidTransactions);

        return transactionValidationResponse;
    }

    public TransactionFilterResponse filterTransactions(@Valid TransactionFilterRequest transactionFilterRequest) {
        List<Transaction> transactionsList = transactionFilterRequest.getTransactions();
        Set<Transaction> seen = new HashSet<>();
        Set<Transaction> invalidSeen= new HashSet<>();
        List<KPeriod> kPeriod = transactionFilterRequest.getK();

        List<InvalidTransaction> invalidTransactions = getInvalidTransactions(transactionsList, invalidSeen);

        List<Transaction> duplicateTransactions = getDuplicateTransactions(transactionsList, invalidSeen, seen);


        List<Transaction> valid = seen.stream()
                .filter(t->isInAnyKPeriod(t.getDate(),kPeriod))
                .toList();
        List<ValidTransactionFilterResponse> validTransactionFilterResponses = new ArrayList<>();

        for (Transaction t:valid){
            ValidTransactionFilterResponse response = new ValidTransactionFilterResponse();
            response.setInKPeriod(true);
            response.setAmount(t.getAmount());
            response.setCeiling(t.getCeiling());
            response.setDate(t.getDate());
            response.setRemanent(t.getRemanent());
            validTransactionFilterResponses.add(response);

        }
        TransactionFilterResponse response = new TransactionFilterResponse();
        response.setValid(validTransactionFilterResponses);
        response.setInvalid(invalidTransactions);
        return response;
    }
    private boolean isInAnyKPeriod(LocalDateTime txDate, List<KPeriod> kPeriods) {
        if (kPeriods == null || kPeriods.isEmpty()) return false;
        return kPeriods.stream()
                .anyMatch(k -> isInRange(txDate, k.getStart(), k.getEnd()));
    }
    private boolean isInRange(LocalDateTime date, LocalDateTime start, LocalDateTime end) {
        return !date.isBefore(start) && !date.isAfter(end);
    }
    private  List<Transaction> getDuplicateTransactions(List<Transaction> transactionsList, Set<Transaction> invalidSeen, Set<Transaction> seen) {
        List<Transaction> duplicateTransactions=
                transactionsList
                        .stream()
                        .filter(t->!invalidSeen.contains(t))
                        .filter(t->!seen.add(t))
                        .toList();
        return duplicateTransactions;
    }

    private  List<InvalidTransaction> getInvalidTransactions(List<Transaction> transactionsList, Set<Transaction> invalidSeen) {
        List<InvalidTransaction> invalidTransactions =
                transactionsList.
                        stream()
                        .filter(t->t.getAmount()<0||t.getRemanent()>t.getCeiling())
                        .map(t->{
                            InvalidTransaction invalidTransaction = new InvalidTransaction();
                            invalidTransaction.setAmount(t.getAmount());
                            invalidTransaction.setCeiling(t.getCeiling());
                            invalidTransaction.setDate(t.getDate());
                            invalidTransaction.setRemanent(t.getRemanent());
                            invalidTransaction.setMessage("Negative Amounts not Allowed or Remanent cannot be greater than ceiling");
                            invalidSeen.add(t);
                            return invalidTransaction;
                        }).toList();
        return invalidTransactions;
    }
}
