package com.guilherme.payment.payment_provider.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PaymentDTO {
    private String id;
    private String value;
    private String currency;
}
