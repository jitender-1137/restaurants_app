package com.restaurants.app.co;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;

import com.restaurants.app.annotations.EnsureNumber;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class QrCodeCo {

	@NotBlank
	private String tableName;

	@NotBlank
	@EnsureNumber
	private String tableNo;

}
