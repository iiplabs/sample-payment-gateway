package com.iiplabs.spg.web.model.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.iiplabs.spg.web.validators.CardExpired;

import org.hibernate.validator.constraints.CreditCardNumber;

import lombok.Data;

@Data
public class CardDto implements Serializable {
  
    @NotNull(message="{validation.invalid_pan}")
    @CreditCardNumber(message="{validation.invalid_pan}")
    private String pan;

    @NotNull(message="{validation.invalid_expiry}")
    @Size(min=4, max=4, message="{validation.invalid_expiry}")
    @CardExpired
    private String expiry;

    @NotNull(message="{validation.invalid_cvv}")
    @Size(min=3, max=3, message="{validation.invalid_cvv}")
    private String cvv;
    
}
