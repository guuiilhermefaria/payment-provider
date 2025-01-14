package com.guilherme.payment.payment_provider.mapper;

import com.guilherme.payment.payment_provider.dto.PaymentDTO;
import com.guilherme.payment.payment_provider.model.Payment;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PaymentRequestMapper {
    PaymentRequestMapper PAYMENT_REQUEST_MAPPER = Mappers.getMapper(PaymentRequestMapper.class);

    Payment paymentDTOToPayment(PaymentDTO orderRequest);
}
