package com.shop.repository;

import com.querydsl.core.QueryResults;
import com.shop.dto.ItemSearchDto;
import com.shop.dto.MemberSearchDto;
import com.shop.entity.Item;
import com.shop.entity.Member;
import com.shop.entity.QItem;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
	Member findByEmail(String email);

//	@Override
//	public Page<Item> getAdminItemPage(ItemSearchDto itemSearchDto, Pageable pageable) {
//		QueryResults<Item> results = queryFactory.selectFrom(QItem.item)
//				.where(regDtsAfter(itemSearchDto.getSearchDateType()),
//						searchSellStatusEq(itemSearchDto.getSearchSellStatus()),
//						searchByLike(itemSearchDto.getSearchBy(), itemSearchDto.getSearchQuery()))
//				.orderBy(QItem.item.id.desc()).offset(pageable.getOffset()).limit(pageable.getPageSize())
//				.fetchResults();
//		List<Item> content = results.getResults();
//		long total = results.getTotal();
//		return new PageImpl<>(content, pageable, total);
//	}
}
