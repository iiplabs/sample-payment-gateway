package com.iiplabs.spg.web.model.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.iiplabs.spg.web.validators.CardExpired;

import org.hibernate.validator.constraints.CreditCardNumber;

import lombok.Data;

@Data
public class CardDto implements Serializable {
  
    @NotNull(message="{validation.invalid_pan}")
    @CreditCardNumber(message="{validation.invalid_pan}")
    private String pan;

    @NotNull(message="{validation.invalid_expiry}")
    @Pattern(regexp="^\\d{4}$", message="{validation.invalid_expiry}")
    @CardExpired
    private String expiry;

    @NotNull(message="{validation.invalid_cvv}")
    @Pattern(regexp="^\\d{3}$", message="{validation.invalid_cvv}")
    private String cvv;
    
}
