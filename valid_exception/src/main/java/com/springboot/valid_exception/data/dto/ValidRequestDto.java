package com.springboot.valid_exception.data.dto;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import com.springboot.valid_exception.data.group.ValidationGroup1;
import com.springboot.valid_exception.data.group.ValidationGroup2;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ValidRequestDto {

	// @Null : Null값만 허용 @NotNull : Null을 허용하지 않음. "", " "은 허용
	// @NotEmpty : null, ""을 허용하지 않음, " "sms gjdyd @NotBlank : null, "", " "모두 허용하지 않음
	@NotBlank
	private String name;
	
	// @Email : 이메일 형식 검사. ""는 허용
	@Email
	private String email;
	
	//@Pattern : 정규식 검사
	@Pattern(regexp = "01(?:0|1|[6-9])[.-]?(\\d{3}|\\d{4})[.-]?(\\d{4})$")
	//@Telephone
	private String phoneNumber;
	
	// DecimalMin(value = "$numberString") : $numberString 이상의 값을 허용
	// DecimalMax(value = "$numberString") : $numberString 이하의 값을 허용
	// @Max(value = $number) : $number 이상의 값을 허용 @Max(value = $number) : $number이하의 값을 허용
	@Min(value = 20)
	@Max(value = 40)
	private int age;
	
	// @Size(min = $minNumber, max = $maxNumber) : 문자열의 길이를 제한
	@Size(min = 0, max = 40)
	private String description;
	
	// @Positive : 양수를 허용 @PositiveOrZero : 0을 포함한 양수를 허용
	// @Nagative : 음수를 허용 @NagetiveOrZero : 0을 포함한 음수를 허용
	@Positive
	private int count;
	
	// @AssertTrue : true 체크, null값은 체크하지 않음 @AssertFalse : false 체크, null값은 체크하지 않음
	@AssertTrue
	private boolean booleanCheck;
	
	//@Future : 현재보다 미래의 날짜를 허용 @FutureOrPresent : 현재를 포함한 미래의 날짜를 허용
	//@Past : 현재보다 과거의 날짜를 허용 @PastOrPresent : 현재를 포함한 과거의 날짜를 허용
	//Date birthDay;
	
	//@Digits : 수치의 범위를 설정
	// private Integer digits;
	
}
