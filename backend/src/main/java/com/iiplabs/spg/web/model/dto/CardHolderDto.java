package com.iiplabs.spg.web.model.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@JsonPropertyOrder({"name", "email"})
public record CardHolderDto(
        @NotNull(message = "{validation.invalid_name}") @Size(min = 1, max = 100, message = "{validation.invalid_name}") String name,
        @NotBlank(message = "{validation.invalid_email}") @Email(message = "{validation.invalid_email}") String email) {
}
