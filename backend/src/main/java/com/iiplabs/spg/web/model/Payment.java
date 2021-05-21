package com.iiplabs.spg.web.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

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
	
	@ManyToOne
	@JoinColumn(name="card")
	@Fetch(FetchMode.JOIN)
	private Card card;

	@JsonProperty("cardholder")
	@ManyToOne
	@JoinColumn(name="card_holder")
	@Fetch(FetchMode.JOIN)
	private CardHolder cardHolder;
	
}
