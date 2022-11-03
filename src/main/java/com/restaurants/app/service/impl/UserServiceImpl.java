package com.restaurants.app.service.impl;

import com.restaurants.app.co.UserCo;
import com.restaurants.app.dao.UserDao;
import com.restaurants.app.domains.User;
import com.restaurants.app.dto.ComparativeRelationAndValue;
import com.restaurants.app.dto.UserDto;
import com.restaurants.app.service.UserService;
import com.restaurants.app.dto.EntryItem;
import com.restaurants.app.dto.GenericSearchFilter;
import com.restaurants.app.utils.CommonUtil;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private MessageSource messageSource;

    @Override
//    @Cacheable(value = "employee", key = "#genericSearchFilter.toString() + #pageNumber + #pageSize")
    public EntryItem<UserDto> getEmployee(GenericSearchFilter genericSearchFilter, Integer pageNumber, Integer pageSize) {
        Map<String, ComparativeRelationAndValue> searchParams = genericSearchFilter.getSearchParams();
        log.info("Fetching Employee as per following queries : {}", searchParams);
        List<UserDto> userDtoList = new LinkedList<>();
        long start = System.currentTimeMillis();
//        EntryItem<Employee> employeeEntryItem = employeeDao.findByCriteriaFields(genericSearchFilter, pageNumber, pageSize);
//        log.info("Time taken to get employees from employeeService /  cache= {} ms", (System.currentTimeMillis() - start));
//        if (!CollectionUtils.isEmpty(employeeEntryItem.getItems())) {
//            employeeEntryItem.getItems().forEach(employee -> {
//                EmployeeDto employeeDto = modelMapper.map(employee, EmployeeDto.class);
//                employeeDtoList.add(employeeDto);
//            });
//        }
//        return new EntryItem<>(employeeEntryItem.getTotalItemsCount(), employeeDtoList);
        return null;
    }

    @Override
    public UserDto saveEmployee(@Valid UserCo userCo) {

        User user = CommonUtil.convert(userCo, User.class);

        user = userDao.save(user);
        return CommonUtil.convert(user, UserDto.class);
    }

    @Override
    public Map<String, Map> updateEmployee(List<UserCo> userCos) {
        return null;
    }

    @Override
    public UserDto updateEmployeeById(Long employeeId, UserCo userCo) {
        return null;
    }

    @Override
    public Object doDeactivation(Long employeeId) {
        return null;
    }

}
