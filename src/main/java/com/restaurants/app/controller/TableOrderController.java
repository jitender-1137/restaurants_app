package com.restaurants.app.controller;

import com.restaurants.app.dto.CommonObjectDto;
import com.restaurants.app.dto.ResponseDto;
import com.restaurants.app.dto.SuccessResponseDto;
import com.restaurants.app.service.impl.TableOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1")
public class TableOrderController {

    @Autowired
    protected TableOrderService tableOrderService;

    @PostMapping("/validateTable")
    public ResponseDto validateTable(String qrCode) {
        CommonObjectDto response = tableOrderService.validateTable(qrCode);
        return new SuccessResponseDto("Order is placed !!!");
    }
}
