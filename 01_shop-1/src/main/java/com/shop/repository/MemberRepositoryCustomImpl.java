package com.shop.repository;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.thymeleaf.util.StringUtils;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.shop.constant.Role;
import com.shop.dto.MemberSearchDto;
import com.shop.entity.Member;
import com.shop.entity.QMember;

public class MemberRepositoryCustomImpl implements MemberRepositoryCustom {
	private JPAQueryFactory queryFactory;
	
	public MemberRepositoryCustomImpl(EntityManager em) {
		this.queryFactory = new JPAQueryFactory(em);
	}private BooleanExpression searchUserStatusEq(Role searchUserStatus){
return searchUserStatus == null ? null : QMember.member.role.eq(searchUserStatus);
	}
	private BooleanExpression regDtsAfter(String searchDateType) {
		LocalDateTime dateTime = LocalDateTime.now();
		if (StringUtils.equals("all", searchDateType) || searchDateType == null) {
			return null;
		} else if (StringUtils.equals("1d", searchDateType)) {
			dateTime = dateTime.minusDays(1);
		} else if (StringUtils.equals("1w", searchDateType)) {
			dateTime = dateTime.minusWeeks(1);
		} else if (StringUtils.equals("1m", searchDateType)) {
			dateTime = dateTime.minusMonths(1);
		} else if (StringUtils.equals("6m", searchDateType)) {
			dateTime = dateTime.minusMonths(6);
		}
		return QMember.member.regTime.after(dateTime);
	}	

	private BooleanExpression searchByLike(String searchBy, String searchQuery) {
		if (StringUtils.equals("name", searchBy)) {
			return QMember.member.name.like("%" + searchQuery + "%");
		} else if (StringUtils.equals("createdBy", searchBy)) {
			return QMember.member.createdBy.like("%" + searchQuery + "%");
		}
		return null;
	}


	@Override
	public Page<Member> getAdminMemberPage(MemberSearchDto memberSearchDto, Pageable pageable) {
		QueryResults<Member> results = queryFactory.selectFrom(QMember.member)
				.where(regDtsAfter(memberSearchDto.getSearchDateType()),
						searchUserStatusEq(memberSearchDto.getSearchUserStatus()),
						searchByLike(memberSearchDto.getSearchBy(), memberSearchDto.getSearchQuery()))
				.orderBy(QMember.member.id.desc()).offset(pageable.getOffset()).limit(pageable.getPageSize())
				.fetchResults();
		List<Member> content = results.getResults();
		long total = results.getTotal();
		return new PageImpl<>(content, pageable, total);
	}

	
}