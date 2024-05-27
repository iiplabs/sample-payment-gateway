package com.iiplabs.spg.web.model.dto;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public record TransactionResponseDto(
        Map<String, String> errors) {

    public boolean isApproved() {
        return errors == null || errors.isEmpty();
    }

}
