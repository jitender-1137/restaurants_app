package com.restaurants.app.service.impl;

import com.restaurants.app.dto.CommonObjectDto;

public interface TableOrderService {
    CommonObjectDto validateTable(String qrCode);
}
