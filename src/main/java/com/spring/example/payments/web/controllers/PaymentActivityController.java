package com.spring.example.payments.web.controllers;

import com.spring.example.api.model.Error;
import com.spring.example.api.web.response.factory.ApiResponseFactory;
import com.spring.example.api.web.response.model.ApiResponse;
import com.spring.example.api.web.response.model.ErrorResponseDetail;
import com.spring.example.payments.models.PaymentActivity;
import com.spring.example.payments.services.PaymentActivityService;
import com.spring.example.payments.transformers.PaymentActivityResourceTransformer;
import com.spring.example.payments.web.models.PaymentActivityResource;
import com.spring.example.payments.web.validators.PaymentActivityValidator;
import io.swagger.annotations.ApiOperation;
import io.vavr.collection.Seq;
import io.vavr.control.Either;
import io.vavr.control.Validation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("v1/payments")
public class PaymentActivityController {

    private final ApiResponseFactory apiResponseFactory;
    private final PaymentActivityService paymentActivityService;
    private final PaymentActivityResourceTransformer paymentActivityResourceTransformer;
    private final PaymentActivityValidator paymentActivityValidator;

    public PaymentActivityController(final ApiResponseFactory apiResponseFactory,
                                     final PaymentActivityService paymentActivityService,
                                     final PaymentActivityResourceTransformer paymentActivityResourceTransformer,
                                     final PaymentActivityValidator paymentActivityValidator) {
        this.apiResponseFactory = apiResponseFactory;
        this.paymentActivityService = paymentActivityService;
        this.paymentActivityResourceTransformer = paymentActivityResourceTransformer;
        this.paymentActivityValidator = paymentActivityValidator;
    }

    @ApiOperation(value = "Get payment activities")
    @GetMapping("/payment-activities")
    public Mono<ApiResponse> getPaymentActivities() {

        final Either<Error, Flux<PaymentActivity>> paymentActivitiesHolder = paymentActivityService.getPaymentActivities();

        if (paymentActivitiesHolder.isLeft()) {
            return Mono.just(apiResponseFactory.buildUnprocessableEntityErrorResponse(paymentActivitiesHolder.getLeft()));
        }

        final Flux<PaymentActivityResource> paymentActivityResource = paymentActivitiesHolder
                .map(paymentActivity -> paymentActivity.map(paymentActivityResourceTransformer::toResource))
                .get();

        return Mono.just(apiResponseFactory.buildOkResponse(paymentActivityResource));
    }

    @ApiOperation(value = "Create a payment activity")
    @PostMapping("/payment-activities")
    public Mono<ApiResponse> createPaymentActivity(@RequestBody final PaymentActivityResource paymentActivityResource) {

        final Validation<Seq<ErrorResponseDetail>, PaymentActivityResource> requestValidationResult = paymentActivityValidator.validate(paymentActivityResource);

        if (requestValidationResult.isInvalid()) {
            return Mono.just(apiResponseFactory.buildBadRequestResponse(requestValidationResult.getError().toJavaList()));
        }

        final PaymentActivity paymentActivity = paymentActivityResourceTransformer.fromResource(paymentActivityResource);

        final Either<Error, Mono<Void>> paymentActivityHolder = paymentActivityService.savePaymentActivity(paymentActivity);

        if (paymentActivityHolder.isLeft()) {
            return Mono.just(apiResponseFactory.buildUnprocessableEntityErrorResponse(paymentActivityHolder.getLeft()));
        }

        return Mono.just(apiResponseFactory.buildCreatedResponse(null));
    }

}
