package com.restaurants.app.service;

import javax.validation.Valid;

import com.restaurants.app.co.CategoriesCo;
import com.restaurants.app.dto.CommonObjectDto;

public interface CategoriesService {

	CommonObjectDto addCategory(@Valid CategoriesCo categoriesCo);

	CommonObjectDto getAllCategories();

}
