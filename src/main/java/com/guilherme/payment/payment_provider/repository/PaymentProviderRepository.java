package com.guilherme.payment.payment_provider.repository;

import com.guilherme.payment.payment_provider.model.Payment;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PaymentProviderRepository extends MongoRepository<Payment, String> {
}
