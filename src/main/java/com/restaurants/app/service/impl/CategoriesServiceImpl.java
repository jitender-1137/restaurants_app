package com.restaurants.app.service.impl;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restaurants.app.co.CategoriesCo;
import com.restaurants.app.dao.CategoriesDao;
import com.restaurants.app.domains.Categories;
import com.restaurants.app.dto.CommonObjectDto;
import com.restaurants.app.exceptions.ServiceException;
import com.restaurants.app.service.CategoriesService;

@Service
public class CategoriesServiceImpl implements CategoriesService {

	@Autowired
	protected CategoriesDao categoriesDao;

	@Override
	public CommonObjectDto addCategory(@Valid CategoriesCo categoriesCo) {
		Categories categories = new Categories();
		CommonObjectDto commonObjectDto = new CommonObjectDto();

		categories.setCategoryName(categoriesCo.getCategoryName());
		categories.setChildCategory(categoriesCo.getChildCategory());
		categories.setParentCategory(categoriesCo.getParentCategory());
		categories.setUpdtedAt(System.currentTimeMillis());
		categories.setCreatedAt(System.currentTimeMillis());
		categories.setActive(true);
		categories.setExists(true);

		Categories categories2 = categoriesDao.save(categories);

		if (categories2.getCategoryId() == null || categories2.getCategoryId() == 0) {
			throw new ServiceException("005");
		}

		commonObjectDto.setData(categories2);

		return commonObjectDto;
	}

	@Override
	public CommonObjectDto getAllCategories() {
		CommonObjectDto commonObjectDto = new CommonObjectDto();

		List<Categories> categoriesList = categoriesDao.findAll();
		commonObjectDto.setData(categoriesList);
		return commonObjectDto;
	}

}
