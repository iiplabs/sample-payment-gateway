package com.iiplabs.spg.web.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Embeddable;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.iiplabs.spg.web.utils.serializers.AllMaskSerializer;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter 
@Setter 
@RequiredArgsConstructor 
@ToString(callSuper=true) 
@EqualsAndHashCode(onlyExplicitlyIncluded=true)
@SuppressWarnings("serial")
@Embeddable
public class CardHolder implements Serializable {

	@JsonSerialize(using=AllMaskSerializer.class)
	@EqualsAndHashCode.Include
	@Convert(converter=EncryptedContentConverter.class)
	@Column(name="name")
	private String name;

	@EqualsAndHashCode.Include
	@Column(name="email")
	private String email;

}
