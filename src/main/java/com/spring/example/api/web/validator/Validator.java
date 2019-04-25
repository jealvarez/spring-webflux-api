package com.spring.example.api.web.validator;

import io.vavr.control.Validation;

/**
 * @param <I> - Object to Validate
 * @param <E> - Error
 */
@FunctionalInterface
public interface Validator<E, I> {

    Validation<E, I> validate(I objectToValidate);

}
