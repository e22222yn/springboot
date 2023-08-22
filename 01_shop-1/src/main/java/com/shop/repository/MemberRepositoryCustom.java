package com.shop.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.shop.dto.MemberSearchDto;
import com.shop.entity.Member;

public interface MemberRepositoryCustom {
	Page<Member> getAdminMemberPage(MemberSearchDto memberSearchDto, Pageable pageable);

//	Page<MainItemDto> getMainItemPage(ItemSearchDto itemSearchDto, Pageable pageable);
}