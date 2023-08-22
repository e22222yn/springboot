package com.springboot.security.data.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table
public class User implements UserDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(nullable = false, unique = true)
	private String uid; //회원 id(JWT 토근 내 정보)
	
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@Column(nullable = false)
	private String password;
	
	@Column(nullable = false)
	private String name;
	
	@ElementCollection(fetch = FetchType.EAGER)	//컬렉션 타입의 컬럼임을 알림
	@Builder.Default	//객체를 원하는 타입과 값으로 초기화할 때  (=빌더패턴을 통해 인스턴스를 만들 때 특정필드를 특정값으로 초기화하고 싶다면)
	private List<String> roles = new ArrayList();
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities(){	// 계정이 가지고 있는 권한 목록 리턴
		return this.roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
		
	}
	
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@Override
	public String getUsername() {
		return this.uid;	// security에서 사용하는 회원 구분 id
	}
	
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@Override
	public boolean isAccountNonExpired() {
		return true;	//계정이 만료되었는지 체크하는 로직. 이 예제에서는 사용하지 않으므로 true값 리턴
	}
	
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@Override
	public boolean isAccountNonLocked() {
		return true;	//계정이 잠겼는지 체크하는 로직. 해당 예제에서는 사용하지 않으므로 true값 리턴
	}
	
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@Override
	public boolean isCredentialsNonExpired() {
		return true;	//계정이 만료되었는지 체크하는 로직. 해당 예제에서는 사용하지 않으므로 true값 리턴
	}
	
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@Override
	public boolean isEnabled() {
		return true;	//계정이 사용 가능한지 체크하는 로직. 해당 예제에서는 사용하지 않으므로 true값 리턴
	}
	
}