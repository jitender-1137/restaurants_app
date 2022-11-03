package com.restaurants.app.service.impl;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.restaurants.app.co.QrCodeCo;
import com.restaurants.app.dto.CommonObjectDto;
import com.restaurants.app.service.QrCodeGeneratorService;

@Service
public class QrCodeGeneratorServiceImpl implements QrCodeGeneratorService {
	BitMatrix bitMatrix = null;
	static ByteArrayOutputStream os = new ByteArrayOutputStream();

	@SuppressWarnings("deprecation")
	@Override
	public CommonObjectDto generateQR(QrCodeCo qrCodeCo) {

		String rootPath = "C:\\Users\\Lenovo\\Documents\\QrCode";
		try {
			BitMatrix matrix;
			String returnFilePath = null;
			String urlEncode = null;
			String data = null;
			int qrCodewidth = 125;
			int qrCodeheight = 125;

			String vpaId = "merchant.s5@getepay";
			urlEncode = URLEncoder.encode(data, "UTF-8").replace(" ", "%20");

			System.out.println(urlEncode);
			String finalUrl = data;
			String filePath = vpaId + ".png";
			String charset = "UTF-8";
			@SuppressWarnings("rawtypes")
			Map<EncodeHintType, Comparable> hintMap = new HashMap<EncodeHintType, Comparable>();
			hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.Q);
			hintMap.put(EncodeHintType.MARGIN, 1);

			try {
				String sourcePath = rootPath;
				returnFilePath = "QRimage" + File.separator + filePath;
				File isDir = new File(sourcePath);
				if (!isDir.exists()) {
					new File(sourcePath).mkdirs();
				}
				filePath = sourcePath + File.separator + filePath;
				matrix = new MultiFormatWriter().encode(new String(finalUrl.getBytes(charset), charset),
						BarcodeFormat.QR_CODE, qrCodewidth, qrCodeheight, hintMap);

				MatrixToImageWriter.writeToFile(matrix, filePath.substring(filePath.lastIndexOf('.') + 1),
						new File(filePath));
				System.out.println("returnFilePath ==> " + returnFilePath);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (WriterException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}