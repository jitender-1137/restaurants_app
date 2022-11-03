package com.restaurants.app.co;

import javax.validation.constraints.NotBlank;

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
	private String tableNo;

}
