package com.restaurants.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.restaurants.app.domains.User;

@Repository
public interface UserDao extends JpaRepository<User, Long> {

    User findByUserName(String username);

//    EntryItem<Employee> findByCriteriaFields(GenericSearchFilter genericSearchFilter, Integer pageNumber, Integer pageSize);
}
