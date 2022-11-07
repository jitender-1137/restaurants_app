package com.restaurants.app.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.restaurants.app.enums.CategoryLevel;
import com.restaurants.app.utils.CommonUtil;
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

        String categoryName = validteCategoryName(categoriesCo);
        validateCategoryLevel(categoriesCo);

        categories.setCategoryName(categoriesCo.getCategoryName());
        categories.setSlug(categoryName);
        categories.setParentCategory(categoriesCo.getParentCategory());
        categories.setUpdtedAt(System.currentTimeMillis());
        categories.setCreatedAt(System.currentTimeMillis());
        categories.setLevel(categoriesCo.getLevel());
        categories.setActive(true);
        categories.setExists(true);

        Categories categories2 = categoriesDao.save(categories);

        if (categories2.getCategoryId() == null || categories2.getCategoryId() == 0) {
            throw new ServiceException("005");
        }

        commonObjectDto.setData(categories2);
        return commonObjectDto;
    }

    private String validteCategoryName(CategoriesCo categoriesCo) {
        String categoryName = "";
        categoryName = CommonUtil.removeWhiteSpace(categoriesCo.getCategoryName());
        categoryName = CommonUtil.toSmallerCase(categoryName);

        Categories categories = categoriesDao.findByCategoryName(categoryName);
        if (categories != null)
            throw new ServiceException("014");
        return categoryName;
    }

    private void validateCategoryLevel(CategoriesCo categoriesCo) {
        Categories exsistingCategory = new Categories();
        if (categoriesCo.getParentCategory() != null && categoriesCo.getParentCategory() != 0) {
            exsistingCategory = categoriesDao.findByParentId(categoriesCo.getParentCategory());
            if (exsistingCategory == null) throw new ServiceException("006");
        }

        if (!(CategoryLevel.L1.level.equals(categoriesCo.getLevel()) || CategoryLevel.L2.level.equals(categoriesCo.getLevel()) || CategoryLevel.L3.level.equals(categoriesCo.getLevel())))
            throw new ServiceException("011");

        else if (CategoryLevel.L1.level.equals(categoriesCo.getLevel())) {
            if (categoriesCo.getParentCategory() != null && categoriesCo.getParentCategory() != 0)
                throw new ServiceException("007");

        } else if (CategoryLevel.L2.level.equals(categoriesCo.getLevel())) {
            if (categoriesCo.getParentCategory() == null || categoriesCo.getParentCategory() == 0)
                throw new ServiceException("013");
            else if (!CategoryLevel.L1.level.equals(exsistingCategory.getLevel())) {
                throw new ServiceException("012");
            }
        } else if (CategoryLevel.L3.level.equals(categoriesCo.getLevel())) {
            if (categoriesCo.getParentCategory() == null || categoriesCo.getParentCategory() == 0)
                throw new ServiceException("013");
            else if (!CategoryLevel.L2.level.equals(exsistingCategory.getLevel())) throw new ServiceException("010");
        }
    }

    @Override
    public CommonObjectDto getAllCategories(Integer perPage, Integer pageSize) {
        CommonObjectDto commonObjectDto = new CommonObjectDto();

        List<Categories> categoriesList = categoriesDao.findAllExistingCategories(perPage, pageSize);
        commonObjectDto.setData(categoriesList);
        return commonObjectDto;
    }

    @Override
    public void deleteCategory(Long id) {
        Categories categories = new Categories();
        categories = categoriesDao.findByIdAndExist(id);
        if (categories != null) {
            categories.setUpdtedAt(System.currentTimeMillis());
            categories.setActive(false);
            categories.setExists(false);
            categoriesDao.save(categories);
        } else
            throw new ServiceException("015");
    }

    @Override
    public CommonObjectDto updateCategory(List<CategoriesCo> categoriesCos) {
        Categories categories = new Categories();
        List<Categories> categoriesList = new ArrayList<>();

        for (CategoriesCo categoriesCo : categoriesCos) {
            String categoryName = validteCategoryName(categoriesCo);
            validateCategoryLevel(categoriesCo);

            categories = CommonUtil.convert(categoriesCo, Categories.class);

            categories.setSlug(categoryName);
            categories.setUpdtedAt(System.currentTimeMillis());
            categoriesList.add(categories);

        }
        List<Categories> categoriesList1 = categoriesDao.saveAll(categoriesList);
        CommonObjectDto commonObjectDto = new CommonObjectDto();
        commonObjectDto.setData(categoriesList1);
        return commonObjectDto;
    }

}
