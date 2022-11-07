package com.restaurants.app.service.impl;

import com.restaurants.app.dao.TableOrdeDao;
import com.restaurants.app.domains.TableQrCode;
import com.restaurants.app.dto.CommonObjectDto;
import com.restaurants.app.utils.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TableOrderServiceImpl implements TableOrderService {

    @Autowired
    protected TableOrdeDao tableOrdeDao;
    @Override
    public CommonObjectDto validateTable(String qrCode) {
        TableQrCode tableQrCode1 = CommonUtil.convertToObject(qrCode, TableQrCode.class);

        return null;
    }
}
