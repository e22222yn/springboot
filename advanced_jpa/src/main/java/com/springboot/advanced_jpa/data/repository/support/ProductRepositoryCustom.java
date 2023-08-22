package com.springboot.advanced_jpa.data.repository.support;

import java.util.List;

import com.springboot.advanced_jpa.data.entity.Product;

/**
* 필요한 쿼리를 작성할 메소드를 정의하는 인터페이스
*/
public interface ProductRepositoryCustom {
List<Product> findByName(String name);
}
