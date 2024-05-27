package com.iiplabs.spg.web.model.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.iiplabs.spg.web.validators.CardExpired;
import com.iiplabs.spg.web.validators.CardExpiryInvalid;

import org.hibernate.validator.constraints.CreditCardNumber;

@JsonPropertyOrder({"pan", "expiry", "cvv"})
public record CardDto(
        @NotNull(message = "{validation.invalid_pan}") @CreditCardNumber(message = "{validation.invalid_pan}") String pan,
        @CardExpiryInvalid @CardExpired String expiry,
        @NotNull(message = "{validation.invalid_cvv}") @Pattern(regexp = "^\\d{3}$", message = "{validation.invalid_cvv}") String cvv) {
}
