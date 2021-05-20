package com.iiplabs.spg.web.model.dto;

import java.io.Serializable;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.iiplabs.spg.web.validators.Amount;
import com.iiplabs.spg.web.validators.Currency;

import lombok.Data;

@Data
public class PaymentDto implements Serializable {

    @NotNull(message="{validation.invalid_invoice}")
    @Size(min=1, max=50, message="{validation.invalid_invoice}")
    private String invoice;

    @Amount
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
