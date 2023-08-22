package com.springboot.security.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	private final JwtTokenProvider jwtTokenProvider;
	
	@Autowired
	public SecurityConfiguration(JwtTokenProvider jwtTokenProvider) {
		this.jwtTokenProvider = jwtTokenProvider;
	}
	
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception{
		httpSecurity.httpBasic().disable()	//REST API는 csrf 보안이 필요없으므로 비활성화

			.csrf().disable()

			.sessionManagement()
			.sessionCreationPolicy(
					SessionCreationPolicy.STATELESS)	//JWT Token 인증방식으로 세션은 필요없으므로 비활성화
			.and()
			.authorizeRequests()	//Request에 대한 사용권한 체크
			.antMatchers("/sign-api/sign-in", "/sign-api/sign-up", "/sign-api/exception").permitAll()	// 가입 및 로그인 주소는 허용
			.antMatchers(HttpMethod.GET, "/product/**").permitAll()										// product로 시작하는 Get 요청은 허용
			.antMatchers("**exception**").permitAll()
			.anyRequest().hasRole("ADMIN")	//나머지 요청은 인증된 ADMIN만 접근 가능
		
			.and()
			.exceptionHandling().accessDeniedHandler(new CustomAccessDeniedHandler())
		
			.and()
			.exceptionHandling().authenticationEntryPoint(new CustomAuthenticationEntryPoint())
		
			.and()
			.addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), 
					UsernamePasswordAuthenticationFilter.class);	//JWT Token 필터를 id/pswd 인증 필터 이 전에 추가
		
	}
	
	// Swagger 페이지 접근에 대한 예외처리
	@Override
	public void configure(WebSecurity webSecurity) {
		webSecurity.ignoring().antMatchers("/v2/api-docs", "/swagger-resources/**", "/swagger-ui.html", "/webjars/**", "/swagger/**", "/sign-api/exception");
		
	}
}
