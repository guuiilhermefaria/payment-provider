package com.guilherme.payment.payment_provider.client;

import com.guilherme.payment.payment_provider.dto.PaymentDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "payment-consumer", url = "http://localhost:8081/api/payment-consumer")
public interface PaymentClient {
    @GetMapping("/find/{id}")
    PaymentDTO getPaymentById(@PathVariable("id") String id);
}
