package com.spring.example.payments.transformers;

import com.spring.example.payments.models.PaymentActivity;
import com.spring.example.payments.web.models.PaymentActivityResource;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static org.mapstruct.InjectionStrategy.CONSTRUCTOR;

@Mapper(
        injectionStrategy = CONSTRUCTOR,
        componentModel = "spring"
)
public interface PaymentActivityResourceTransformer {

    @Mapping(source = "paymentInstrumentType", target = "paymentInstrumentType")
    PaymentActivity fromResource(PaymentActivityResource paymentActivityResource);

    @Mapping(source = "paymentInstrumentType", target = "paymentInstrumentType")
    PaymentActivityResource toResource(PaymentActivity paymentActivity);

}
