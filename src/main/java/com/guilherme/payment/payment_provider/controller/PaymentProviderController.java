package com.guilherme.payment.payment_provider.controller;

import com.guilherme.payment.payment_provider.dto.PaymentDTO;
import com.guilherme.payment.payment_provider.service.PaymentProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payment-provider")
public class PaymentProviderController {
    @Autowired
    private PaymentProviderService paymentProviderService;

    @PostMapping("/save")
    public ResponseEntity<PaymentDTO> save(@RequestBody final PaymentDTO payment) {
        return ResponseEntity.ok(paymentProviderService.save(payment));
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<PaymentDTO> findById(@PathVariable final String id) {
        return ResponseEntity.ok(paymentProviderService.findById(id));
    }
}
