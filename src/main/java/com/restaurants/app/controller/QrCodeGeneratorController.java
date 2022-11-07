package com.restaurants.app.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.restaurants.app.co.TableQrCodeCo;
import com.restaurants.app.dto.CommonObjectDto;
import com.restaurants.app.dto.ResponseDto;
import com.restaurants.app.dto.SuccessResponseDto;
import com.restaurants.app.service.QrCodeGeneratorService;

import javax.validation.Valid;

@RestController
@RequestMapping("v1")
@Validated
@Slf4j
public class QrCodeGeneratorController {

    @Autowired
    protected QrCodeGeneratorService qrCodeGeneratorService;

    @PostMapping("/generateQR")
    public ResponseDto generateQR(@Valid @RequestBody TableQrCodeCo tableQrCodeCo) {
        CommonObjectDto commonObjectDto = qrCodeGeneratorService.generateNewQR(tableQrCodeCo);
        return new SuccessResponseDto("Table QR Code generate successfully");
    }

    @GetMapping("/getQrCode")
    public ResponseDto fetchData(){
        CommonObjectDto commonObjectDto = qrCodeGeneratorService.fetchAllQrCodes();
        return new SuccessResponseDto(commonObjectDto.getData(), "Fetch Data Successfully");
    }

    @DeleteMapping("/deleteQrCode/{id}")
    public ResponseDto deleteQRCode(@PathVariable Long id){
        qrCodeGeneratorService.deleteQrCode(id);
        return new SuccessResponseDto(id,"Delete Successfully");
    }
}