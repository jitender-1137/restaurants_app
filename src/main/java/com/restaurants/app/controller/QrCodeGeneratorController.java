package com.restaurants.app.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.restaurants.app.co.QrCodeCo;
import com.restaurants.app.dto.CommonObjectDto;
import com.restaurants.app.dto.ResponseDto;
import com.restaurants.app.dto.SuccessResponseDto;
import com.restaurants.app.service.QrCodeGeneratorService;

@RestController
@RequestMapping("v1")
public class QrCodeGeneratorController {

	@Autowired
	protected QrCodeGeneratorService qrCodeGeneratorService;

	@PostMapping("/generateQR")
	public ResponseDto<Object> generateQR(@RequestBody QrCodeCo qrCodeCo) {

		CommonObjectDto objectDto = qrCodeGeneratorService.generateQR(qrCodeCo);

		return new SuccessResponseDto<>("Table QR Code generate successfully");
	}
}