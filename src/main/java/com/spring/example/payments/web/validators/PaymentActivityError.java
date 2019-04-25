package com.spring.example.payments.web.validators;

import com.spring.example.api.model.Error;

public enum PaymentActivityError implements Error {

    UNSUPPORTED_PAYMENT_INSTRUMENT("Unsupported payment instrument type"),
    INVALID_PAYMENT_INSTRUMENT_IS_EMPTY("Invalid payment instrument type, must not be empty"),
    INVALID_INVOICE_NUMBER_IS_EMPTY("Invalid invoice number, must not be empty"),
    INVALID_CURRENCY_IS_EMPTY("Invalid currency, must not be empty"),
    INVALID_AMOUNT_IS_OR_LOWER_THAN_ZERO("Invalid amount, must not be lower or equals than zero");

    private final String message;

    PaymentActivityError(final String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
