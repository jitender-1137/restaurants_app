package com.restaurants.app.co;

import javax.validation.constraints.NotBlank;

import com.restaurants.app.domains.Items;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class OrderCo {

	@NotBlank
	private String name;

	@NotBlank
	private String tableNo;
	
	private Items items;
}
