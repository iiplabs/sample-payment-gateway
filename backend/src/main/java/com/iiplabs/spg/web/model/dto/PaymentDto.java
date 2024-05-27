package com.iiplabs.spg.web.model.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.iiplabs.spg.web.validators.Currency;

@JsonPropertyOrder({"invoice", "amount", "currency", "card", "cardholder"})
public record PaymentDto(
        @NotNull(message = "{validation.invalid_invoice}") @Size(min = 1, max = 50, message = "{validation.invalid_invoice}") String invoice,
        @NotNull(message = "{validation.invalid_amount}") @Pattern(regexp = "^\\d*[1-9]\\d*$", message = "{validation.invalid_amount}") String amount,
        @Currency String currency,
        @JsonProperty("cardholder") @Valid CardHolderDto cardHolder,
        @Valid CardDto card) {

    @JsonIgnore
    public int getIntAmount() {
        return Integer.parseInt(amount);
    }

}
