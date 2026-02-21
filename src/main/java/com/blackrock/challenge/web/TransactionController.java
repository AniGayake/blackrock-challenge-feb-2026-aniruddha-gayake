package com.blackrock.challenge.web;

import com.blackrock.challenge.dto.request.ExpensesRequest;
import com.blackrock.challenge.dto.response.ApiResponse;
import com.blackrock.challenge.dto.response.EnrichedTransactionResponse;
import com.blackrock.challenge.service.TransactionEnrichCoreService;
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
    private TransactionEnrichCoreService transactionEnrichCoreService;

    @Autowired
    public TransactionController(TransactionEnrichCoreService transactionEnrichCoreService) {
        this.transactionEnrichCoreService = transactionEnrichCoreService;
    }

    @PostMapping("/transactions:parse")
    public ResponseEntity<ApiResponse<List<EnrichedTransactionResponse>>> parseTransactions(
            @Valid @RequestBody final List<ExpensesRequest> expense){

        LOGGER.info("Expenses list received");
        List<EnrichedTransactionResponse> transactionResponses =transactionEnrichCoreService.enrichTransactions(expense);
        LOGGER.info("Returning ResponseList to user");
        return ResponseEntity.ok(ApiResponse.success("Transactions Enriched Successfully",transactionResponses));
    }

}
