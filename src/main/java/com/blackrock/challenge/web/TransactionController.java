package com.blackrock.challenge.web;

import com.blackrock.challenge.dto.request.ExpensesRequest;
import com.blackrock.challenge.dto.request.TransactionFilterRequest;
import com.blackrock.challenge.dto.request.TransactionValidationRequest;
import com.blackrock.challenge.dto.response.ApiResponse;
import com.blackrock.challenge.dto.response.TransactionFilterResponse;
import com.blackrock.challenge.dto.response.TransactionResponse;
import com.blackrock.challenge.dto.response.TransactionValidationResponse;
import com.blackrock.challenge.service.TransactionCoreService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/blackrock/challenge/v1")
public class TransactionController {
    private static final Logger LOGGER = LoggerFactory.getLogger(TransactionController.class);
    private final TransactionCoreService transactionCoreService;

    @Autowired
    public TransactionController(TransactionCoreService transactionCoreService) {
        this.transactionCoreService = transactionCoreService;
    }

    @PostMapping("/transactions:parse")
    public ResponseEntity<ApiResponse<List<TransactionResponse>>> parseTransactions(
            @Valid @RequestBody final List<ExpensesRequest> expense){

        LOGGER.info("Expenses list received");
        List<TransactionResponse> transactionResponses = transactionCoreService.enrichTransactions(expense);
        LOGGER.info("Returning ResponseList to user");
        return ResponseEntity.ok(ApiResponse.success("Transactions Enriched Successfully",transactionResponses));
    }

    @PostMapping("/transactions:validator")
    public ResponseEntity<ApiResponse<TransactionValidationResponse>> validateTransactions(
            @Valid @RequestBody final TransactionValidationRequest transactionValidationRequest){

        TransactionValidationResponse response = transactionCoreService.validateTransactions(transactionValidationRequest);
        return ResponseEntity.ok(ApiResponse.success("Validated",response));
    }

    @PostMapping("/transactions:filter")
    public ResponseEntity<ApiResponse<TransactionFilterResponse>> filterTransactions(@Valid @RequestBody TransactionFilterRequest transactionFilterRequest){

        TransactionFilterResponse response = transactionCoreService.filterTransactions(transactionFilterRequest);
        return ResponseEntity.ok(ApiResponse.success("Filtered",response));
    }

}
