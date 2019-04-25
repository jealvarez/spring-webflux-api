package com.spring.example.payments.services;

import com.spring.example.api.model.Error;
import com.spring.example.payments.models.PaymentActivity;
import com.spring.example.payments.repositories.PaymentActivityRepository;
import io.vavr.control.Either;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PaymentActivityService {

    private final PaymentActivityRepository paymentActivityRepository;

    public PaymentActivityService(final PaymentActivityRepository paymentActivityRepository) {
        this.paymentActivityRepository = paymentActivityRepository;
    }

    public Either<Error, Flux<PaymentActivity>> getPaymentActivities() {
        return Either.right(Flux.fromIterable(paymentActivityRepository.findAll()));
    }

    public Either<Error, Mono<Void>> savePaymentActivity(final PaymentActivity paymentActivity) {
        paymentActivityRepository.insert(paymentActivity);
        return Either.right(Mono.empty());
    }

}
