package com.restaurants.app.service.impl;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.restaurants.app.dao.QrCodeGeneratorDao;
import com.restaurants.app.domains.QrCode;
import com.restaurants.app.exceptions.ServiceException;
import com.restaurants.app.utils.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    protected QrCodeGeneratorDao qrCodeGeneratorDao;

    BitMatrix bitMatrix = null;
    static ByteArrayOutputStream os = new ByteArrayOutputStream();

    @SuppressWarnings("deprecation")
    @Override
    public CommonObjectDto generateQR(QrCodeCo qrCodeCo) {
        QrCode qrCode = new QrCode();

        String imageUrl = QrCodeUrl(qrCodeCo);

        qrCode.setEnable(true);
        qrCode.setCreatedAt(System.currentTimeMillis());
        qrCode.setUpdatedAt(qrCode.getCreatedAt());
        qrCode.setTableNo(qrCodeCo.getTableNo());
        qrCode.setTableName(qrCodeCo.getTableName());
        qrCode.setUrl(imageUrl);

        QrCode ouptput = qrCodeGeneratorDao.save(qrCode);
        if (ouptput.getQrCodeId() == null || ouptput.getQrCodeId() == 0) {
            throw new ServiceException("002");
        }

        return null;
    }

    private String QrCodeUrl(QrCodeCo qrCodeCo) {
        String imageUrl = null;
        String rootPath = "C:\\Users\\Lenovo\\Documents\\QrCode";
        try {
            BitMatrix matrix;
            String returnFilePath = null;
            String urlEncode = null;
            String data = null;
            int qrCodewidth = 125;
            int qrCodeheight = 125;

            QrCode qrCode = new QrCode();
            data = CommonUtil.convertToString(qrCode);

            urlEncode = URLEncoder.encode(data, "UTF-8").replace(" ", "%20");

            System.out.println(urlEncode);
            String finalUrl = data;
            String filePath = qrCodeCo.getTableName() + "_" + qrCodeCo.getTableNo() + ".png";
            String charset = "UTF-8";

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
                URL url = new URL("http://example.com/" + returnFilePath);
                imageUrl = String.valueOf(url);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                throw new ServiceException("003");
            } catch (WriterException e) {
                e.printStackTrace();
                throw new ServiceException(e.getMessage());
            } catch (IOException e) {
                e.printStackTrace();
                throw new ServiceException(e.getMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException("003");
        }
        return imageUrl;
    }
}