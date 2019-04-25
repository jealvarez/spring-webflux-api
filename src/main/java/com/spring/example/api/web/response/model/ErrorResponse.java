package com.spring.example.api.web.response.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.spring.example.api.model.Error;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;
import static java.util.Collections.emptyList;
import static java.util.Collections.unmodifiableList;

@Builder
@Getter
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonInclude(NON_EMPTY)
@JsonPropertyOrder({"name", "details", "message"})
public class ErrorResponse {

    private final String name;
    private final String message;

    @JsonProperty("details")
    private final List<ErrorResponseDetail> errorResponseDetails;

    @JsonIgnore
    private final Error error;

    public ErrorResponse(final Error error, final List<ErrorResponseDetail> errorResponseDetails) {
        this.error = error;
        this.name = error.toString();
        this.errorResponseDetails = errorResponseDetails;
        this.message = error.getMessage();
    }

    public ErrorResponse(final Error error) {
        this(error, unmodifiableList(emptyList()));
    }

}
