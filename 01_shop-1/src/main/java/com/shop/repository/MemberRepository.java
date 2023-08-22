package com.shop.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.shop.dto.MemberSearchDto;
import com.shop.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
	Member findByEmail(String email);

	Page<Member> getAdminMemberPage(MemberSearchDto memberSearchDto, Pageable pageable);

}
