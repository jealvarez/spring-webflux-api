package com.spring.example.payments.web.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentActivityResource {

    private String invoiceNumber;
    private String currency;
    private BigDecimal amount;
    private String paymentInstrumentType;

}

