package com.restaurants.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.restaurants.app.domains.Categories;

@Repository
public interface CategoriesDao extends JpaRepository<Categories, Long> {

}
