package com.guilherme.payment.payment_provider.service;

import com.guilherme.payment.payment_provider.client.PaymentClient;
import com.guilherme.payment.payment_provider.dto.PaymentDTO;
import com.guilherme.payment.payment_provider.exceptions.ResourceAlreadyExistsException;
import com.guilherme.payment.payment_provider.exceptions.ResourceNotFoundException;
import com.guilherme.payment.payment_provider.model.Payment;
import com.guilherme.payment.payment_provider.producer.KafkaDispatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import static com.guilherme.payment.payment_provider.mapper.PaymentRequestMapper.PAYMENT_REQUEST_MAPPER;

@Service
public class PaymentProviderService {
    private static final Logger logger = LoggerFactory.getLogger(PaymentProviderService.class);

    private final Map<String, PaymentDTO> paymentStorage = new HashMap<>();

    @Autowired
    private PaymentClient paymentClient;

    private KafkaDispatcher kafkaDispatcher = new KafkaDispatcher();

    public void save(PaymentDTO paymentDTO) throws ExecutionException, InterruptedException {
        String paymentId = paymentDTO.getId();

        if (paymentStorage.containsKey(paymentId)) {
            throw new ResourceAlreadyExistsException("Payment with ID " + paymentId + " already exists.");
        }

        paymentStorage.put(paymentId, paymentDTO);
        Payment payment = PAYMENT_REQUEST_MAPPER.paymentDTOToPayment(paymentDTO);

        logger.info("Sending payment with ID {} to Kafka topic", paymentId);

        kafkaDispatcher.send("payment", paymentId, payment);

        logger.info("Payment with ID {} saved successfully", paymentId);
    }

    public PaymentDTO findById(String id) {
        PaymentDTO paymentById = paymentClient.getPaymentById(id);
        if (paymentById == null) {
            throw new ResourceNotFoundException("Payment with ID " + id + " not found.");
        }

        return paymentById;
    }
}
