package com.iiplabs.spg.web.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter 
@Setter 
@RequiredArgsConstructor 
@ToString 
@EqualsAndHashCode(onlyExplicitlyIncluded=true)
@MappedSuperclass
@SuppressWarnings("serial")
public abstract class BaseModel implements Serializable {

	@JsonIgnore
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private long id;

	@JsonIgnore
	@Version
	@Column(name="optlock")
	private int version;
	
}
