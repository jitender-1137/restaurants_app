package com.restaurants.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.restaurants.app.domains.Categories;

import java.util.List;

@Repository
public interface CategoriesDao extends JpaRepository<Categories, Long> {

    @Query(value = "SELECT c FROM Categories c WHERE c.categoryId=?1 AND c.isExists = true")
    Categories findByParentId(Long parentCategory);

    @Query(value = "SELECT c FROM Categories c WHERE c.slug=?1 AND c.isExists = true ")
    Categories findByCategoryName(String categoryName);

    @Query(value = "SELECT c FROM Categories c WHERE c.isExists=true")
    List<Categories> findAllExistingCategories(Integer perPage, Integer pageSize);

    @Query(value = "SELECT c FROM Categories c WHERE c.isExists = true AND c.categoryId=?1")
    Categories findByIdAndExist(Long id);
}
