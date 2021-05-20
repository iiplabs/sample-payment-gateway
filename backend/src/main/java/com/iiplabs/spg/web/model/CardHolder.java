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
@Table(name="cardholders")
public class CardHolder extends BaseModel {

	@JsonSerialize(using=AllMaskSerializer.class)
	@EqualsAndHashCode.Include
	@Convert(converter=EncryptedContentConverter.class)
	@Column(name="name")
	private String name;

	@EqualsAndHashCode.Include
	@Convert(converter=EncryptedContentConverter.class)
	@Column(name="email")
	private String email;

	@JsonIgnore
	@OneToMany(mappedBy="card", fetch=FetchType.EAGER, cascade=CascadeType.ALL, orphanRemoval=true)
	private Set<Payment> payments = new HashSet<>();

	public void addPayment(Payment payment) {
		payments.add(payment);
	}

}
