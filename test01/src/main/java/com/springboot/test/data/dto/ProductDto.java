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
public class ProductDto {
	
	private String name;
	private int price;
	private int stock;
	
	public ProductDto() {
	}
	
	public ProductDto(String name, int price, int stock) {
		this.name = name;
		this.price = price;
		this.stock = stock;
	}



		
}
