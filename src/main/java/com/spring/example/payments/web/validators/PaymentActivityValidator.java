package com.spring.example.payments.web.validators;

import com.spring.example.api.model.Error;
import com.spring.example.api.web.response.factory.ErrorResponseFactory;
import com.spring.example.api.web.response.model.ErrorResponseDetail;
import com.spring.example.api.web.validator.Validator;
import com.spring.example.payments.enums.PaymentInstrumentType;
import com.spring.example.payments.transformers.PaymentActivityResourceTransformer;
import com.spring.example.payments.web.models.PaymentActivityResource;
import io.vavr.collection.Seq;
import io.vavr.control.Validation;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

import static com.spring.example.payments.web.validators.PaymentActivityError.INVALID_AMOUNT_IS_OR_LOWER_THAN_ZERO;
import static com.spring.example.payments.web.validators.PaymentActivityError.INVALID_CURRENCY_IS_EMPTY;
import static com.spring.example.payments.web.validators.PaymentActivityError.INVALID_INVOICE_NUMBER_IS_EMPTY;
import static com.spring.example.payments.web.validators.PaymentActivityError.INVALID_PAYMENT_INSTRUMENT_IS_EMPTY;
import static com.spring.example.payments.web.validators.PaymentActivityError.UNSUPPORTED_PAYMENT_INSTRUMENT;
import static io.vavr.control.Validation.combine;
import static io.vavr.control.Validation.invalid;
import static io.vavr.control.Validation.valid;
import static java.math.RoundingMode.HALF_UP;
import static org.springframework.util.StringUtils.isEmpty;

@Component
public class PaymentActivityValidator implements Validator<Seq<ErrorResponseDetail>, PaymentActivityResource> {

    private final PaymentActivityResourceTransformer paymentActivityResourceTransformer;
    private final ErrorResponseFactory errorResponseFactory;

    public PaymentActivityValidator(final PaymentActivityResourceTransformer paymentActivityResourceTransformer,
                                    final ErrorResponseFactory errorResponseFactory) {
        this.paymentActivityResourceTransformer = paymentActivityResourceTransformer;
        this.errorResponseFactory = errorResponseFactory;
    }

    @Override
    public Validation<Seq<ErrorResponseDetail>, PaymentActivityResource> validate(final PaymentActivityResource paymentActivityResourceToValidate) {

//        final Try<PaymentActivity> paymentActivityHolder = Try.of(() -> paymentActivityResourceTransformer.fromResource(paymentActivityResourceToValidate));
//
//        if (paymentActivityHolder.isFailure()) {
//            return invalid(List.of(errorResponseFactory.buildErrorResponseDetail(paymentActivityResourceToValidate.getPaymentInstrumentType(), UNSUPPORTED_PAYMENT_INSTRUMENT)));
//        }
//
//        final PaymentActivity paymentActivity = paymentActivityHolder.get();

        final Validation<ErrorResponseDetail, String> invoiceNumberValidationResult = validateIsEmpty(paymentActivityResourceToValidate.getInvoiceNumber(), INVALID_INVOICE_NUMBER_IS_EMPTY);
        final Validation<ErrorResponseDetail, String> currencyValidationResult = validateIsEmpty(paymentActivityResourceToValidate.getCurrency(), INVALID_CURRENCY_IS_EMPTY);
        final Validation<ErrorResponseDetail, BigDecimal> amountValidationResult = validateNumber(paymentActivityResourceToValidate.getAmount());
        final Validation<ErrorResponseDetail, String> paymentInstrumentTypeValidationResult = validatePaymentInstrumentType(paymentActivityResourceToValidate.getPaymentInstrumentType());

        return combine(
                invoiceNumberValidationResult,
                currencyValidationResult,
                amountValidationResult,
                paymentInstrumentTypeValidationResult
        ).ap(PaymentActivityResource::new);


//        return valid(paymentActivityResourceToValidate);
    }

    private Validation<ErrorResponseDetail, String> validatePaymentInstrumentType(final String string) {
        if (isEmpty(string)) {
            return invalid(errorResponseFactory.buildErrorResponseDetail(string, INVALID_PAYMENT_INSTRUMENT_IS_EMPTY));
        }

        final PaymentInstrumentType paymentInstrumentType = PaymentInstrumentType.nullSafeValueOf(string);

        if (isEmpty(paymentInstrumentType)) {
            return invalid(errorResponseFactory.buildErrorResponseDetail(string, UNSUPPORTED_PAYMENT_INSTRUMENT));
        }

        return valid(string);
    }

    private Validation<ErrorResponseDetail, String> validateIsEmpty(final String string, final Error error) {
        if (isEmpty(string)) {
            return invalid(errorResponseFactory.buildErrorResponseDetail(string, error));
        }

        return valid(string);
    }

    private Validation<ErrorResponseDetail, BigDecimal> validateNumber(final BigDecimal number) {
        final BigDecimal amount = number.setScale(2, HALF_UP);
        if (amount.compareTo(new BigDecimal("0.00")) <= 0) {
            return invalid(errorResponseFactory.buildErrorResponseDetail(String.valueOf(number), INVALID_AMOUNT_IS_OR_LOWER_THAN_ZERO));
        }

        return valid(number);
    }

}
