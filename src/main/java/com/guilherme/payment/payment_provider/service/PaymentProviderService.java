package com.guilherme.payment.payment_provider.service;

import com.guilherme.payment.payment_provider.dto.PaymentDTO;
import com.guilherme.payment.payment_provider.exceptions.ResourceAlreadyExistsException;
import com.guilherme.payment.payment_provider.exceptions.ResourceNotFoundException;
import com.guilherme.payment.payment_provider.model.Payment;
import com.guilherme.payment.payment_provider.repository.PaymentProviderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

import static com.guilherme.payment.payment_provider.mapper.PaymentRequestMapper.PAYMENT_REQUEST_MAPPER;
import static com.guilherme.payment.payment_provider.mapper.PaymentResponseMapper.PAYMENT_RESPONSE_MAPPER;

@Service
public class PaymentProviderService {
    private final Map<String, PaymentDTO> paymentStorage = new HashMap<>();

    @Autowired
    private PaymentProviderRepository paymentProviderRepository;

    public PaymentDTO save(PaymentDTO paymentDTO) {
        String paymentId = paymentDTO.getId();

        if (paymentStorage.containsKey(paymentId)) {
            throw new ResourceAlreadyExistsException("Payment with ID " + paymentId + " already exists.");
        }

        paymentStorage.put(paymentId, paymentDTO);
        Payment payment = PAYMENT_REQUEST_MAPPER.paymentDTOToPayment(paymentDTO);
        Payment savedPayment = paymentProviderRepository.save(payment);

        return PAYMENT_RESPONSE_MAPPER.paymentToPaymentDTO(savedPayment);
    }

    public PaymentDTO findById(String id) {
        return paymentProviderRepository.findById(id)
                .map(PAYMENT_RESPONSE_MAPPER::paymentToPaymentDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Payment with ID " + id + " not found."));
    }
}
