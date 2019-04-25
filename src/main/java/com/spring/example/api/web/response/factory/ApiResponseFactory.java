package com.spring.example.api.web.response.factory;

import com.spring.example.api.model.Error;
import com.spring.example.api.web.response.model.ApiResponse;
import com.spring.example.api.web.response.model.ErrorResponseDetail;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.spring.example.api.model.ErrorCode.INVALID_REQUEST;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

@Component
public class ApiResponseFactory {

    private final ErrorResponseFactory errorResponseFactory;

    public ApiResponseFactory(final ErrorResponseFactory errorResponseFactory) {
        this.errorResponseFactory = errorResponseFactory;
    }

    public <T> ApiResponse buildOkResponse(final T body) {
        return new ApiResponse(body, OK);
    }

    public <T> ApiResponse buildCreatedResponse(final T body) {
        return new ApiResponse(body, CREATED);
    }

    public ApiResponse buildBadRequestResponse(final List<ErrorResponseDetail> errorResponseDetails) {
        return new ApiResponse(errorResponseFactory.buildErrorResponse(INVALID_REQUEST, errorResponseDetails), BAD_REQUEST);
    }

    public ApiResponse buildBadRequestResponse(final Error error) {
        return new ApiResponse(errorResponseFactory.buildErrorResponse(error), BAD_REQUEST);
    }

    public ApiResponse buildInternalServerErrorResponse(final Error error) {
        return new ApiResponse(errorResponseFactory.buildErrorResponse(error), INTERNAL_SERVER_ERROR);
    }

    public ApiResponse buildUnprocessableEntityErrorResponse(final Error error) {
        return new ApiResponse(errorResponseFactory.buildErrorResponse(error), UNPROCESSABLE_ENTITY);
    }

}
