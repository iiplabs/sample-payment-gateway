package com.iiplabs.spg.web.model.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class CardHolderDto implements Serializable {

    @NotNull(message="{validation.invalid_name}")
    @Size(min=1, max=100, message="{validation.invalid_name}")
    private String name;
    
    @NotNull(message="{validation.invalid_email}")
    @Email(message="{validation.invalid_email}")
    private String email;

}
