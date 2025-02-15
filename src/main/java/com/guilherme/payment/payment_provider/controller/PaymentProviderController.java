package com.guilherme.payment.payment_provider.controller;

import com.guilherme.payment.payment_provider.dto.PaymentDTO;
import com.guilherme.payment.payment_provider.exceptions.ApiResponse;
import com.guilherme.payment.payment_provider.exceptions.ResourceAlreadyExistsException;
import com.guilherme.payment.payment_provider.service.PaymentProviderService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payment-provider")
public class PaymentProviderController {
    private static final Logger logger = LoggerFactory.getLogger(PaymentProviderController.class);

    @Autowired
    private PaymentProviderService paymentProviderService;

    @PostMapping("/save")
    public ResponseEntity<ApiResponse> save(@RequestBody @Valid PaymentDTO paymentDTO) {
        try {
            paymentProviderService.save(paymentDTO);
            ApiResponse response = new ApiResponse("Payment saved successfully", HttpStatus.CREATED.value());
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (ResourceAlreadyExistsException e) {
            logger.error("Payment already exists: {}", paymentDTO.getId(), e);
            ApiResponse errorResponse = new ApiResponse(e.getMessage(), HttpStatus.BAD_REQUEST.value());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        } catch (Exception e) {
            logger.error("Error saving payment: {}", paymentDTO, e);
            ApiResponse errorResponse = new ApiResponse("An error occurred while saving the payment.", HttpStatus.INTERNAL_SERVER_ERROR.value());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<PaymentDTO> findById(@PathVariable final String id) {
        return ResponseEntity.ok(paymentProviderService.findById(id));
    }
}
