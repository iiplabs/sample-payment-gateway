package com.iiplabs.spg.web.model.dto;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class TransactionResponseDto {
  
    Map<String, String> errors;
    
    public boolean isApproved() {
        return errors == null || errors.isEmpty();
    }

}
