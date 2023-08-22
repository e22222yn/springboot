package com.springboot.test.service.impl;

import com.springboot.test.data.dto.ProductResponseDto;
import com.springboot.test.data.entity.Product;
import com.springboot.test.data.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.verify;
import java.util.Optional;


public class ProductServiceTest {
	
	private ProductRepository productRepository = Mockito.mock(ProductRepository.class);
	private ProductServiceImpl productService;
	@BeforeEach
	public void setUpTest() {
	productService = new ProductServiceImpl(productRepository);
	}
	@Test
	void getProductTest() {
	// given
	Product givenProduct = new Product();
	givenProduct.setNumber(123L);
	givenProduct.setName("펜");
	givenProduct.setPrice(1000);
	givenProduct.setStock(1234);
	Mockito.when(productRepository.findById(123L))
	.thenReturn(Optional.of(givenProduct));
	// when
	ProductResponseDto productResponseDto = productService.getProduct(123L);
	// then
	Assertions.assertEquals(productResponseDto.getNumber(), givenProduct.getNumber());
	Assertions.assertEquals(productResponseDto.getName(), givenProduct.getName());
	Assertions.assertEquals(productResponseDto.getPrice(), givenProduct.getPrice());
	Assertions.assertEquals(productResponseDto.getStock(), givenProduct.getStock());
	verify(productRepository).findById(123L);
	}
}
