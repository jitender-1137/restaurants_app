package com.restaurants.app.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.restaurants.app.dao.QrCodeGeneratorDao;
import com.restaurants.app.dao.TableOrdeDao;
import com.restaurants.app.domains.QrCode;
import com.restaurants.app.domains.TableOrder;
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
        QrCode qrCode1 = CommonUtil.convertToObject(qrCode, QrCode.class);

        return null;
    }
}
