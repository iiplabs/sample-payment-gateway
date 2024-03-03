package com.iiplabs.spg.web.model.dto;

import java.io.Serializable;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import lombok.Data;

@Data
public class CardHolderDto implements Serializable {

    @NotNull(message = "{validation.invalid_name}")
    @Size(min = 1, max = 100, message = "{validation.invalid_name}")
    private String name;

    @NotBlank(message = "{validation.invalid_email}")
    @Email(message = "{validation.invalid_email}")
    private String email;

}
