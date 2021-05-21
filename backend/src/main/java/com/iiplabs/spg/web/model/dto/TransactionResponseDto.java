package com.iiplabs.spg.web.model.dto;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@Jacksonized
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public final class TransactionResponseDto {
  
    Map<String, String> errors;
    
    public boolean isApproved() {
        return errors == null || errors.isEmpty();
    }

}
