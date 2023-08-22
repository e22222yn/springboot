package com.shop.repository;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.TestPropertySource;
import org.thymeleaf.util.StringUtils;

import com.querydsl.core.BooleanBuilder;
import com.shop.constant.ItemSellStatus;
import com.shop.entity.Item;
import com.shop.entity.QItem;

@SpringBootTest
@TestPropertySource(locations="classpath:application-test.properties")
public class ItemRepositoryTest {
	
	@Autowired
	ItemRepository itemRepository;
	
	@PersistenceContext
	EntityManager em;		//영속성 컨텍스트 사용 위해 @PersistenceContext를 이용하여 EntityManager 빈을 주입
	
	
	public void createItemList() {
		for(int i = 1; i <= 10; i++) {
			Item item = new Item();
			item.setItemNm("테스트 상품" + i);
			item.setPrice(10000 + i);
			item.setItemDetail("테스트 상품 상세 설명" + i);
			item.setItemSellStatus(ItemSellStatus.SELL);
			item.setStockNumber(100);
			item.setRegTime(LocalDateTime.now());
			item.setUpdateTime(LocalDateTime.now());
			Item savedItem = itemRepository.save(item);
		}
	}
	
	public void createItemList2() {
		for(int i = 1; i <= 5; i++) {
			Item item = new Item();
			item.setItemNm("테스트 상품" + i);
			item.setPrice(10000 + i);
			item.setItemDetail("테스트 상품 상세 설명" + i);
			item.setItemSellStatus(ItemSellStatus.SELL);
			item.setStockNumber(100);
			item.setRegTime(LocalDateTime.now());
			item.setUpdateTime(LocalDateTime.now());
			Item savedItem = itemRepository.save(item);
		}
		for(int i = 6; i <= 10; i++) {
			Item item = new Item();
			item.setItemNm("테스트 상품" + i);
			item.setPrice(10000 + i);
			item.setItemDetail("테스트 상품 상세 설명" + i);
			item.setItemSellStatus(ItemSellStatus.SOLD_OUT);
			item.setStockNumber(0);
			item.setRegTime(LocalDateTime.now());
			item.setUpdateTime(LocalDateTime.now());
			Item savedItem = itemRepository.save(item);
		}
	}
	
//	@Test
//	@DisplayName("상품 저장 테스트")
//	public void createItemTest() {
//		Item item = new Item();
//		item.setItemNm("테스트 상품");
//		item.setPrice(10000);
//		item.setItemDetail("테스트 상품 상세 설명");
//		item.setItemSellStatus(ItemSellStatus.SELL);
//		item.setStockNumber(100);
//		item.setRegTime(LocalDateTime.now());
//		item.setUpdateTime(LocalDateTime.now());
//		Item savedItem = itemRepository.save(item);
//		System.out.println(savedItem.toString());
//	}
	

	
//	@Test
//	@DisplayName("상품명 조회 테스트")
//	public void findByItemNmTest() {
//		this.createItemList();
//		List<Item> itemList = itemRepository.findByItemNm("테스트 상품1");
//		for(Item item : itemList) {
//			System.out.println(item.toString());
//		}
//	}
	
//	@Test
//	@DisplayName("상품명, 상품상세설명 or 테스트")
//	public void findByItemNmOrItemDetailTest() {
//		this.createItemList();
//		List<Item> itemList = itemRepository.findByItemNmOrItemDetail("테스트 상품1", "테스트 상품 상세설명 5");
//		for(Item item : itemList) {
//			System.out.println(item.toString());
//		}
//
//	}
	
//	@Test
//	@DisplayName("가격 LessThan 테스트")
//	public void findByPriceLessThanTest() {
//		this.createItemList();
//		List<Item> itemList = itemRepository.findByPriceLessThan(10005);
//		for(Item item : itemList) {
//			System.out.println(item.toString());
//		}
//
//	}
//	
//	@Test
//	@DisplayName("가격 내림차순 조회 테스트")
//	public void findByPriceLessThanOrderByPriceDesc() {
//		this.createItemList();
//		List<Item> itemList = itemRepository.findByPriceLessThanOrderByPriceDesc(10005);
//		for(Item item : itemList) {
//			System.out.println(item.toString());
//		}
//
//	}
	
//	@Test
//	@DisplayName("@Query를 이용한 상품 조회 테스트")
//	public void findByItemDetailTest() {
//		this.createItemList();
//		List<Item> itemList = itemRepository.findByItemDetail("테스트 상품 상세 설명");
//		for(Item item : itemList) {
//			System.out.println(item.toString());
//		}
//	}
	
//	@Test
//	@DisplayName("nativeQuery 속성을 이용한 상품 조회 테스트")
//	public void findByItemDetailByNative(){
//	this.createItemList();
//	List<Item> itemList = itemRepository.findByItemDetailByNative("테스트 상품 상세 설명");
//	for(Item item : itemList){
//	System.out.println(item.toString());
//	}
//	}
	
	
//	@Test
//	@DisplayName("Querydsl 조회 테스트1")
//	public void queryDslTest() {
//		this.createItemList();
//		JPAQueryFactory queryFactory = new JPAQueryFactory(em);
//			//JPAQuery를 통해 EntityManager 객체 em을 생성자 인자로 하는 쿼리를 통적 생성
//		QItem qItem = QItem.item;
//			// Querydsl을 통해 쿼리를 생성하기 위해 플로그인을 통해 자동 생성된 QItem 객체를 이용
//		JPAQuery<Item> query = queryFactory.selectFrom(qItem)		// 자바 소스코드지만 SQL문과 비슷하게 소스 작성
//				.where(qItem.itemSellStatus.eq(ItemSellStatus.SELL))
//				.where(qItem.itemDetail.like("%" + "테스트 상품 상세 설명" + "%"))
//				.orderBy(qItem.price.desc());
//		
//		List<Item> itemList = query.fetch();		// fatch()를 이용해 쿼리결과를 리스트로 반환
//		
//		for(Item item : itemList) {
//			System.out.println(item.toString());
//		}
//	}
	
	@Test
	@DisplayName("상품 Querydsl 조회 테스트2")
	public void queryDslTest2() {
		this.createItemList2();
		
		BooleanBuilder booleanBuilder = new BooleanBuilder();
		QItem item = QItem.item;
		String itemDetail = "테스트 상품 상세 설명";
		int price = 10003;
		String itemSellStat = "SELL";
		
		booleanBuilder.and(item.itemDetail.like("%" + itemDetail + "%"));
		booleanBuilder.and(item.price.gt(price));
		System.out.println(ItemSellStatus.SELL);
		if(StringUtils.equals(itemSellStat,  ItemSellStatus.SELL)) {
			booleanBuilder.and(item.itemSellStatus.eq(ItemSellStatus.SELL));
		}
		
		Pageable pageable = PageRequest.of(0, 5);
		Page<Item> itemPagingResult = itemRepository.findAll(booleanBuilder, pageable);
		System.out.println("total elements : " + itemPagingResult.getTotalElements());
		
		List<Item> resultItemList = itemPagingResult.getContent();
		for(Item resultItem: resultItemList) {
			System.out.println(resultItem.toString());
		}
		
	}

}
