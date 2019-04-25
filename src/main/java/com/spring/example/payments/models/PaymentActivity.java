package com.spring.example.payments.models;

import com.spring.example.payments.enums.PaymentInstrumentType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
@Getter
@AllArgsConstructor
public class PaymentActivity {

    private final long id;
    private final LocalDate createdAt;
    private final LocalDate updatedAt;
    private final String invoiceNumber;
    private final String currency;
    private final BigDecimal amount;
    private final PaymentInstrumentType paymentInstrumentType;

}
