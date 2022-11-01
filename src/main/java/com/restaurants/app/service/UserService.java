package com.restaurants.app.service;

import com.restaurants.app.co.UserCo;
import com.restaurants.app.dto.UserDto;
import com.restaurants.app.dto.EntryItem;
import com.restaurants.app.dto.GenericSearchFilter;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

public interface UserService {

    UserDto saveEmployee(@Valid UserCo userCo);

    EntryItem<UserDto> getEmployee(GenericSearchFilter genericSearchFilter, Integer pageNumber, Integer pageSize);

    Map<String, Map> updateEmployee(List<UserCo> userCos);

    UserDto updateEmployeeById(Long employeeId, UserCo userCo);

    Object doDeactivation(Long employeeId);

}
