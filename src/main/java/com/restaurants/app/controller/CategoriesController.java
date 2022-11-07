package com.restaurants.app.controller;

import javax.validation.Valid;

import com.restaurants.app.dto.GenericSearchFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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

        return new SuccessResponseDto<>(commonObjectDto.getData(), "Category added successfully");
    }

    @GetMapping("/getCategories")
    public ResponseDto getCategories(@RequestParam(required = false) Integer pageNumber,
                                     @RequestParam(required = false) Integer pageSize) {
        CommonObjectDto commonObjectDto = categoriesService.getAllCategories(pageNumber, pageSize);
        return new SuccessResponseDto<>(commonObjectDto.getData(), "categories fetch successfully");
    }

    @DeleteMapping("/deleteCategory/{id}")
    public ResponseDto deleteCategory(@PathVariable Long id) {
        categoriesService.deleteCategory(id);
        return new SuccessResponseDto("category delete successfully");
    }

}
