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
public class ProductResponseDto {
	private Long number;
	private String name;
	private int price;
	private int stock;
	
	public ProductResponseDto() {}
	
	public ProductResponseDto(Long number, String name, int price, int stock) {
		this.number = number;
		this.name = name;
		this.price = price;
		this.stock = stock;
	}

	
}
