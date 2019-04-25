package com.spring.example.payments.enums;

import org.apache.commons.lang3.EnumUtils;

public enum PaymentInstrumentType {

    CARD,
    ACH;

    public static PaymentInstrumentType nullSafeValueOf(final String value) {
        return EnumUtils.isValidEnum(PaymentInstrumentType.class, value) ? PaymentInstrumentType.valueOf(value) : null;
    }

}
