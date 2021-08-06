package com.iiplabs.spg.web.model;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

import com.fasterxml.jackson.annotation.JsonView;
import com.iiplabs.spg.web.views.Views;
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
@Entity
@Table(name="payments")
public class Payment extends BaseModel {

	@JsonView(Views.Public.class)
	@EqualsAndHashCode.Include
	@Column(name="invoice")
	private String invoice;

	@JsonView(Views.Public.class)
	@EqualsAndHashCode.Include
	@Column(name="amount")
	private int amount;

	@JsonView(Views.Public.class)
	@EqualsAndHashCode.Include
	@Column(name="currency")
	private String currency;

	@JsonView(Views.Public.class)
	@Embedded
	private Card card;

	@JsonView(Views.Public.class)
	@JsonProperty("cardholder")
	@Embedded
	private CardHolder cardHolder;
	
}
