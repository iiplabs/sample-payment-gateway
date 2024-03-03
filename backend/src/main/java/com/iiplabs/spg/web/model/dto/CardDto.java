package com.iiplabs.spg.web.model.dto;

import java.io.Serializable;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import com.iiplabs.spg.web.validators.CardExpired;
import com.iiplabs.spg.web.validators.CardExpiryInvalid;

import org.hibernate.validator.constraints.CreditCardNumber;

import lombok.Data;

@Data
public class CardDto implements Serializable {

    @NotNull(message = "{validation.invalid_pan}")
    @CreditCardNumber(message = "{validation.invalid_pan}")
    private String pan;

    @CardExpiryInvalid
    @CardExpired
    private String expiry;

    @NotNull(message = "{validation.invalid_cvv}")
    @Pattern(regexp = "^\\d{3}$", message = "{validation.invalid_cvv}")
    private String cvv;

}
