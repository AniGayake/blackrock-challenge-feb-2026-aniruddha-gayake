package com.blackrock.challenge.web;

import com.blackrock.challenge.dto.request.ReturnCalculationRequest;
import com.blackrock.challenge.dto.response.ApiResponse;
import com.blackrock.challenge.dto.response.FinancialReturnsResponse;
import com.blackrock.challenge.service.ReturnsCalculationService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/blackrock/challenge/v1")
public class ReturnsCalculatorController {

    private final ReturnsCalculationService returnsCalculationService;

    @Autowired
    public ReturnsCalculatorController(ReturnsCalculationService returnsCalculationService) {
        this.returnsCalculationService = returnsCalculationService;
    }

    @PostMapping("/returns:nps")
    public ResponseEntity<ApiResponse<FinancialReturnsResponse>> calculateReturnsForNPS(@Valid @RequestBody ReturnCalculationRequest request){

        FinancialReturnsResponse response =  returnsCalculationService.calculateReturnsByDates(request);;
        return ResponseEntity.ok(ApiResponse.success("Reports",response));
    }
}
