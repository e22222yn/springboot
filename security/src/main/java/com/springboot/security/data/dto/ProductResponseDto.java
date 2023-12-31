package com.springboot.security.data.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductResponseDto {
	private Long number;
	private String name;
	private int price;
	private int stock;

	public ProductResponseDto() {
	}

	public ProductResponseDto(Long number, String name, int price, int stock) {
		this.number = number;
		this.name = name;
		this.price = price;
		this.stock = stock;
	}

	
}
