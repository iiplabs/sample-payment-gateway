package com.iiplabs.spg.web.model.dto;

import java.io.Serializable;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.iiplabs.spg.web.validators.Currency;

import lombok.Data;

@Data
public class PaymentDto implements Serializable {

    @NotNull(message = "{validation.invalid_invoice}")
    @Size(min = 1, max = 50, message = "{validation.invalid_invoice}")
    private String invoice;

    @NotNull(message = "{validation.invalid_amount}")
    @Pattern(regexp = "^\\d*[1-9]\\d*$", message = "{validation.invalid_amount}")
    private String amount;

    @Currency
    private String currency;

    @JsonProperty("cardholder")
    @Valid
    private CardHolderDto cardHolder;

    @Valid
    private CardDto card;

    @JsonIgnore
    public int getIntAmount() {
        return Integer.parseInt(amount);
    }

}
