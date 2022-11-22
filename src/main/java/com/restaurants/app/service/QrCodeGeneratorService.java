package com.restaurants.app.service;

import com.restaurants.app.co.TableQrCodeCo;
import com.restaurants.app.dto.CommonObjectDto;

import javax.validation.Valid;
import java.util.List;

public interface QrCodeGeneratorService {
    CommonObjectDto generateNewQR(TableQrCodeCo tableQrCodeCo);

    CommonObjectDto fetchAllQrCodes();

    void deleteQrCode(Long id);

    CommonObjectDto updateQrCodes(List<TableQrCodeCo> tableQrCodeCoList);
}
