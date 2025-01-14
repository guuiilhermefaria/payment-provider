package com.guilherme.payment.payment_provider.mapper;

import com.guilherme.payment.payment_provider.dto.PaymentDTO;
import com.guilherme.payment.payment_provider.model.Payment;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PaymentResponseMapper {
    PaymentResponseMapper PAYMENT_RESPONSE_MAPPER = Mappers.getMapper(PaymentResponseMapper.class);

    PaymentDTO paymentToPaymentDTO(Payment orderRequest);
}
