package com.shop.dto;

import com.shop.constant.Role;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberSearchDto {
	private String searchDateType;
	private Role searchUserStatus;
	private String searchBy;
	private String searchQuery = "";
}