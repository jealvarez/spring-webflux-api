package com.spring.example.api.web.response.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.spring.example.api.model.Error;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

@Builder
@Getter
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonInclude(NON_EMPTY)
@JsonPropertyOrder({"value", "issue", "description"})
public class ErrorResponseDetail {

    private final String value;
    private final String issue;
    private final String description;

    @JsonIgnore
    private final Error error;

    public ErrorResponseDetail(final String value, final Error error) {
        this.value = value;
        this.issue = error.toString();
        this.description = error.getMessage();
        this.error = error;
    }

}
