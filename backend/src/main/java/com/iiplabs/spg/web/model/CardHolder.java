package com.iiplabs.spg.web.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Embeddable;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.iiplabs.spg.web.utils.serializers.AllMaskSerializer;

import com.iiplabs.spg.web.views.Views;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@RequiredArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@SuppressWarnings("serial")
@Embeddable
public class CardHolder implements Serializable {

    @JsonView(Views.Public.class)
    @JsonSerialize(using = AllMaskSerializer.class)
    @EqualsAndHashCode.Include
    @Convert(converter = EncryptedContentConverter.class)
    @Column(name = "name")
    private String name;

    @JsonView(Views.Public.class)
    @EqualsAndHashCode.Include
    @Column(name = "email")
    private String email;

}
