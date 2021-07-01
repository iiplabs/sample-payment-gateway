package com.iiplabs.spg.web.model;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

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
@Table(name="payments")
public class Payment extends BaseModel {

	@EqualsAndHashCode.Include
	@Column(name="invoice")
	private String invoice;

	@EqualsAndHashCode.Include
	@Column(name="amount")
	private int amount;
	
	@EqualsAndHashCode.Include
	@Column(name="currency")
	private String currency;

	@Embedded
	private Card card;

	@JsonProperty("cardholder")
	@Embedded
	private CardHolder cardHolder;
	
}
