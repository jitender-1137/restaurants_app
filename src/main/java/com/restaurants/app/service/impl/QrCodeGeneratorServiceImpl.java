package com.restaurants.app.service.impl;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.restaurants.app.dao.QrCodeGeneratorDao;
import com.restaurants.app.domains.TableQrCode;
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
import com.restaurants.app.co.TableQrCodeCo;
import com.restaurants.app.dto.CommonObjectDto;
import com.restaurants.app.service.QrCodeGeneratorService;

import javax.validation.Valid;

@Service
public class QrCodeGeneratorServiceImpl implements QrCodeGeneratorService {

    @Autowired
    protected QrCodeGeneratorDao qrCodeGeneratorDao;

    BitMatrix bitMatrix = null;
    static ByteArrayOutputStream os = new ByteArrayOutputStream();

    @Override
    public CommonObjectDto generateNewQR(TableQrCodeCo tableQrCodeCo) {
        TableQrCode tableQrCode = new TableQrCode();
        CommonObjectDto commonObjectDto = new CommonObjectDto();
        TableQrCode ouptput = null;

        TableQrCode existTableQR = qrCodeGeneratorDao.findByTableNo(Long.valueOf(tableQrCodeCo.getTableNo()));
        if (existTableQR == null) {
            tableQrCode.setEnable(true);
            tableQrCode.setTableNo(Long.valueOf(tableQrCodeCo.getTableNo()));
            tableQrCode.setCreatedAt(System.currentTimeMillis());
            tableQrCode.setUpdatedAt(tableQrCode.getCreatedAt());
            tableQrCode.setTableName(tableQrCodeCo.getTableName());

            String imageUrl = QrCodeUrl(tableQrCodeCo, tableQrCode);
            tableQrCode.setUrl(imageUrl);

            ouptput = qrCodeGeneratorDao.save(tableQrCode);
            if (ouptput.getTableNo() == null || ouptput.getTableNo() == 0) {
                throw new ServiceException("002");
            }
            commonObjectDto.setData(ouptput);

        } else {
            throw new ServiceException("004");
        }
        return commonObjectDto;
    }

    private String QrCodeUrl(TableQrCodeCo tableQrCodeCo, TableQrCode tableQrCode) {
        String imageUrl = null;
        String rootPath = "C:\\Users\\Dell\\Videos\\QrCode";
        try {
            BitMatrix matrix;
            String returnFilePath = null;
            String urlEncode = null;
            String data = null;
            int qrCodewidth = 500;
            int qrCodeheight = 500;

            String filePath1 = tableQrCodeCo.getTableName() + "_" + tableQrCodeCo.getTableNo() + ".png";
            String returnFilePath1 = "QRimage/" + filePath1;
            URL url1 = new URL("http://localhost:8081/v1/categories/getCategories");
            tableQrCode.setUrl(String.valueOf(url1));
            data = CommonUtil.convertToString(tableQrCode);

            urlEncode = URLEncoder.encode(data, "UTF-8").replace(" ", "%20");

            System.out.println(urlEncode);
            String finalUrl = data;
            String filePath = tableQrCodeCo.getTableName() + "_" + tableQrCodeCo.getTableNo() + ".png";
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
                matrix = new MultiFormatWriter().encode(new String(finalUrl.getBytes(charset), charset), BarcodeFormat.QR_CODE, qrCodewidth, qrCodeheight, hintMap);

                MatrixToImageWriter.writeToFile(matrix, filePath.substring(filePath.lastIndexOf('.') + 1), new File(filePath));
                System.out.println("returnFilePath ==> " + returnFilePath);
                URL url = new URL("http://example.com/" + returnFilePath);
                imageUrl = String.valueOf(url1);
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

    @Override
    public CommonObjectDto fetchAllQrCodes() {
        List<TableQrCode> tableQrCodeList = qrCodeGeneratorDao.findAllEnabled();
        CommonObjectDto commonObjectDto = new CommonObjectDto();
        commonObjectDto.setData(tableQrCodeList);

        if (tableQrCodeList.isEmpty())
            throw new ServiceException("016");

        return commonObjectDto;
    }

    @Override
    public void deleteQrCode(Long id) {
        TableQrCode tableQrCode = new TableQrCode();
        tableQrCode = qrCodeGeneratorDao.findByTableNo(id);
        if (tableQrCode != null) {
            tableQrCode.setEnable(false);
            tableQrCode.setUpdatedAt(System.currentTimeMillis());

            tableQrCode = qrCodeGeneratorDao.save(tableQrCode);
            if (tableQrCode == null) {
                throw new ServiceException("017");
            }
        } else {
            throw new ServiceException("018");
        }
    }
}