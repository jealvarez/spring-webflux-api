package com.spring.example.api.web.response.factory;

import com.spring.example.api.model.Error;
import com.spring.example.api.web.response.model.ErrorResponse;
import com.spring.example.api.web.response.model.ErrorResponseDetail;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ErrorResponseFactory {

    ErrorResponse buildErrorResponse(final Error error) {
        return new ErrorResponse(error);
    }

    ErrorResponse buildErrorResponse(final Error error, final List<ErrorResponseDetail> errorResponseDetails) {
        return new ErrorResponse(error, errorResponseDetails);
    }

    public ErrorResponseDetail buildErrorResponseDetail(final String value, final Error error) {
        return new ErrorResponseDetail(value, error);
    }

}
