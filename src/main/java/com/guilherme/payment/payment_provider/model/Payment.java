package com.guilherme.payment.payment_provider.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "payment")
public class Payment {
    private String id;
    private String value;
    private String currency;
}
