package com.springboot.rest.service;

import java.net.URI;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.springboot.rest.dto.MemberDto;

@Service
public class RestTemplateService {

	public String getName() {
		URI uri = UriComponentsBuilder
				.fromUriString("http://localhost:9090")	//호출부 url 입력
				.path("/api/v1/crud-api")	// 세부경로 입력
				.encode()
				.build()
				.toUri();
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> responseEntity = restTemplate.getForEntity(uri, String.class);
		
		return responseEntity.getBody();
				
	}
	
	public String getNameWithPathVariable() {
		URI uri = UriComponentsBuilder
				.fromUriString("http://localhost:9090")	//호출부 url 입력
				.path("/api/v1/crud-api/{name}")	// 세부경로 입력
				.encode()
				.build()
				.expand("Flature")
				.toUri();
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> responseEntity = restTemplate.getForEntity(uri, String.class);
		
		return responseEntity.getBody();
	}
	
	public String getNameWithParameter() {
		URI uri = UriComponentsBuilder
				.fromUriString("http://localhost:9090")	//호출부 url 입력
				.path("/api/v1/crud-api/param")	// 세부경로 입력
				.queryParam("name", "Flature")
				.encode()
				.build()
				.toUri();
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> responseEntity = restTemplate.getForEntity(uri, String.class);
		
		return responseEntity.getBody();
	}
	
	public ResponseEntity<MemberDto> postWithParamAndBody() {
		URI uri = UriComponentsBuilder
				.fromUriString("http://localhost:9090")	//호출부 url 입력
				.path("/api/v1/crud-api")	// 세부경로 입력
				.queryParam("name", "Flature")
				.queryParam("email", "flature@wikibooks.co.kr")
				.queryParam("organization", "wikibooks")
				.encode()
				.build()
				.toUri();
		
		MemberDto memberDto = new MemberDto();
		memberDto.setName("flature!!");
		memberDto.setEmail("flature@gmail.com");
		memberDto.setOrganization("Around Hub Studio");
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<MemberDto> responseEntity = restTemplate.postForEntity(uri, memberDto, MemberDto.class);
		
		return responseEntity;
	}
	
	public ResponseEntity<MemberDto> postWithHeader(){
		URI uri = UriComponentsBuilder
				.fromUriString("http://localhost:9090")	//호출부 url 입력
				.path("/api/v1/crud-api/add=header")	// 세부경로 입력
				.encode()
				.build()
				.toUri();
		
		MemberDto memberDto = new MemberDto();
		memberDto.setName("flature!!");
		memberDto.setEmail("flature@gmail.com");
		memberDto.setOrganization("Around Hub Studio");
		
		RequestEntity<MemberDto> requestEntity = RequestEntity
				.post(uri)
				.header("by-header", "Wikibooks API")
				.body(memberDto);
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<MemberDto> responseEntity = restTemplate.exchange(requestEntity, MemberDto.class);
		
		return responseEntity;
	}
	
	public RestTemplate restTemplate() {
		HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
		
		HttpClient client = HttpClientBuilder.create()
				.setMaxConnTotal(500)
				.setMaxConnPerRoute(500)
				.build();
		
		CloseableHttpClient httpClient = HttpClients.custom()
				.setMaxConnTotal(500)
				.setMaxConnPerRoute(500)
				.build();
		
		factory.setHttpClient(httpClient);
		factory.setConnectTimeout(2000);
		factory.setReadTimeout(5000);
		
		RestTemplate restTemplate = new RestTemplate(factory);
		
		return restTemplate;
	}
}
