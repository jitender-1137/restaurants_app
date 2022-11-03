package com.restaurants.app.service;

import com.restaurants.app.co.QrCodeCo;
import com.restaurants.app.dto.CommonObjectDto;

public interface QrCodeGeneratorService {

	CommonObjectDto generateQR(QrCodeCo qrCodeCo);

}
