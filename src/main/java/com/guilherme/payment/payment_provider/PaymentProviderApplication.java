package com.guilherme.payment.payment_provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class PaymentProviderApplication {

	public static void main(String[] args) {
		SpringApplication.run(PaymentProviderApplication.class, args);
	}

}
