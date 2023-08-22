package com.springboot.advanced_jpa.data.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.springboot.advanced_jpa.data.entity.Product;



public interface ProductRepository extends JpaRepository<Product, Long> {
	// find...By
	Optional<Product> findByNumber(Long number);
	List<Product> findAllByName(String name);
	Product queryByNumber(Long number);
	
	// exists...By
	boolean existsByNumber(Long number);
	// count..By
	long countByName(String name);
	// delete...By, remove...Byvoid
	void deleteByNumber(Long number);
	long removeByName(String name);
	// ...First<number>...,Top<Number>...
	List<Product> findFirst5ByName(String name);
	List<Product> findTop10ByName(String name);
	
	// findByNumber 메서드와 동일하게 동작
	Product findByNumberIs(Long number);
	Product findByNumberEquals(Long number);
	
	Product findByNumberIsNot(Long number);
	Product findByNumberNot(Long number);
	
	List<Product> findByUpdatedAtNull();
	List<Product> findByUpdatedAtIsNull();
	List<Product> findByUpdatedAtNotNull();
	List<Product> findByUpdatedAtIsNotNull();
	
	Product findByNumberAndName(Long number, String name);
	Product findByNumberOrName(Long number, String name);
	
	
	List<Product> findByPriceIsGreaterThan(Long price);
	List<Product> findByPriceGreaterThan(Long price);
	List<Product> findByPriceGreaterThanEqual(Long price);
	List<Product> findByPriceIsLessThan(Long price);
	List<Product> findByPriceLessThan(Long price);
	List<Product> findByPriceLessThanEqual(Long price);
	List<Product> findByPriceIsBetween(Long lowPrice, Long highPrice);
	List<Product> findByPriceBetween(Long lowPrice, Long highPrice);
	
	List<Product> findByNameContains(String name);
	List<Product> findByNameContaining(String name);
	List<Product> findByNameIsContaining(String name);
	List<Product> findByNameStartsWith(String name);
	List<Product> findByNameStartingWith(String name);
	List<Product> findByNameIsStartingWith(String name);
	List<Product> findByNameEndsWith(String name);
	List<Product> findByNameEndingWith(String name);
	List<Product> findByNameIsEndingWith(String name);
	
	// Asc : 오름차순, Desc : 내림차순
	List<Product> findByNameOrderByNumberAsc(String name);
	List<Product> findByNameOrderByNumberDesc(String name);
	
	// And를 붙이지 않음
	List<Product> findByNameOrderByPriceAscStockDesc(String name);
	
	List<Product> findByName(String name, Sort sort);

	Page<Product> findByName(String name, Pageable pageable);

	@Query("SELECT p FROM Product AS p WHERE p.name = ?1")
	List<Product> findByName(String name);
	
	@Query("SELECT p FROM Product p WHERE p.name = :name")
	List<Product> findByNameParam(@Param("name") String name);
	
	@Query("SELECT p.name, p.price, p.stock FROM Product p WHERE p.name = :name")
	List<Object[]> findByNameParam2(@Param("name") String name);
	
	}
