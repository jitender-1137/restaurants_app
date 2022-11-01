package com.restaurants.app.controller;

import com.restaurants.app.co.UserCo;
import com.restaurants.app.dto.*;
import com.restaurants.app.exceptions.ServiceException;
import com.restaurants.app.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/v1")
@Slf4j
@Validated
public class UserController {

    Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/search")
    public ResponseDto getAllEmployee(@RequestBody GenericSearchFilter genericSearchFilter,
                                      @RequestParam(required = false) Integer pageNumber,
                                      @RequestParam(required = false) Integer pageSize) {

        EntryItem<UserDto> employeeDtosEntryItem =
                userService.getEmployee(genericSearchFilter, pageNumber, pageSize);
        return new SuccessResponseDto(employeeDtosEntryItem.getItems(), "Employee Successfully fetched", employeeDtosEntryItem.getTotalItemsCount());
    }


    @RequestMapping("/add")
    public ResponseDto addEmplyee(@RequestBody @Valid UserCo userCo) {
        logger.info("Controller save employee start");
        UserDto userDto2 = userService.saveEmployee(userCo);
        logger.info("Controller save employee stop");
        return new SuccessResponseDto(userDto2, "Employee add successfully");
    }

    @PutMapping
//	@Secured("ROLE_APPROVE_CATEGORY")
    public ResponseDto updateEmployee(@RequestBody List<UserCo> userCos) {
        if (CollectionUtils.isEmpty(userCos))
            throw new ServiceException("CS_02");
        Map<String, Map> updateResult = userService.updateEmployee(userCos);
        return new SuccessResponseDto(updateResult, "Details of updates in response data");
    }

    @PutMapping("/{id}")
//	@Secured("ROLE_EDIT_CATEGORY")
    public ResponseDto update(@PathVariable("id") Long employeeId, @RequestBody @Valid UserCo userCo) {
        UserDto updateCategory = userService.updateEmployeeById(employeeId, userCo);
        return new SuccessResponseDto(updateCategory, "Details of updates in response data");
    }

    @DeleteMapping("/{category-id}")
//	@Secured("ROLE_DELETE_CATEGORY")
    public ResponseDto softDeleteCategory(@PathVariable("category-id") Long employeeId) {
        if (employeeId == null || employeeId == 0)
            throw new ServiceException("CS_03");
        return new SuccessResponseDto(userService.doDeactivation(employeeId), "Category Successfully Soft Deleted");
    }
}
