package com.iiplabs.spg.web.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.iiplabs.spg.web.utils.serializers.AllMaskSerializer;
import com.iiplabs.spg.web.utils.serializers.PanMaskSerializer;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter 
@Setter 
@RequiredArgsConstructor 
@ToString(callSuper=true) 
@EqualsAndHashCode(callSuper=true, onlyExplicitlyIncluded=true)
@SuppressWarnings("serial")
@Entity
@Table(name="cards")
public class Card extends BaseModel {

	@JsonSerialize(using=PanMaskSerializer.class)
	@EqualsAndHashCode.Include
	@Convert(converter=EncryptedContentConverter.class)
	@Column(name="pan")
	private String pan;

	@JsonSerialize(using=AllMaskSerializer.class)
	@EqualsAndHashCode.Include
	@Convert(converter=EncryptedContentConverter.class)
	@Column(name="expiry")
	private String expiry;

	@JsonIgnore
	@OneToMany(mappedBy="card", fetch=FetchType.EAGER, cascade=CascadeType.ALL, orphanRemoval=true)
	private Set<Payment> payments = new HashSet<>();

	public void addPayment(Payment payment) {
		payments.add(payment);
	}

}
