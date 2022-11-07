package com.restaurants.app.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restaurants.app.co.CategoriesCo;
import com.restaurants.app.dto.CommonObjectDto;
import com.restaurants.app.dto.ResponseDto;
import com.restaurants.app.dto.SuccessResponseDto;
import com.restaurants.app.service.CategoriesService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/v1/categories")
@Validated
@Slf4j
public class CategoriesController {
	
	@Autowired
	protected CategoriesService categoriesService;

	@PostMapping("/add")
	public ResponseDto addCategory(@Valid @RequestBody CategoriesCo categoriesCo) {
	
		CommonObjectDto commonObjectDto = categoriesService.addCategory(categoriesCo);
		
		return new SuccessResponseDto<>(commonObjectDto, "Category added successfully");
	}
	
	public ResponseDto getCategories() {
		CommonObjectDto commonObjectDto = categoriesService.getAllCategories();
		return new SuccessResponseDto<>(commonObjectDto.getData(), "categories fetch successfully");
	}

}
