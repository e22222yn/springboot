package com.springboot.security.config.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.security.data.dto.EntryPointErrorResponse;

@Component	//인증 실패시 결과를 처리해주는 로직을 가지고 있는 클래스
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

	private final Logger LOGGER = LoggerFactory.getLogger(CustomAuthenticationEntryPoint.class);
	
	@Override
	public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException{
		ObjectMapper objectMapper = new ObjectMapper();
		LOGGER.info("[commence] 인증 실패로 response.sendError 발생");
		
		EntryPointErrorResponse entryPointErrorResponse = new EntryPointErrorResponse();
		entryPointErrorResponse.setMsg("인증이 실패하였습니다.");
		
		httpServletResponse.setStatus(404);
		httpServletResponse.setContentType("application/json");
		httpServletResponse.setCharacterEncoding("UTF-8");
		httpServletResponse.getWriter().write(objectMapper.writeValueAsString(entryPointErrorResponse));
		
	}
	
//	public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException{
//
//		HttpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED);
//
//	}
}
