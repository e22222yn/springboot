package com.springboot.test.data.dto;

import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Builder
@Getter
@Setter
@EqualsAndHashCode

public class ChangeProductNameDto {
	private Long number;
	private String name;
	
	public ChangeProductNameDto() {
	}
	
	public ChangeProductNameDto(Long number, String Name) {
		this.number = number;
		this.name = name;
	}
}
