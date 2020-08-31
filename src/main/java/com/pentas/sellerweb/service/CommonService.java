package com.pentas.sellerweb.service;

import com.pentas.sellerweb.common.conf.properties.SettingProperties;
import com.pentas.sellerweb.common.dao.CmmnDao;
import com.pentas.sellerweb.common.exception.UserException;
import com.pentas.sellerweb.common.module.util.CmmnUtil;
import com.pentas.sellerweb.common.module.util.DevMap;
import com.pentas.sellerweb.common.module.util.uuid.UuidUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class CommonService {
    private static final Logger log = LoggerFactory.getLogger(CommonService.class);

    private static final int BUFFER_SIZE = 1000000;

    @Autowired
    SettingProperties settingProperties;

    @Autowired
    CmmnDao cmmnDao;

    /**
     * 파일 업로드
     * @param multipartFile
     * @param param
     * @return
     * @throws UserException
     */
    public DevMap uploadFile(MultipartFile multipartFile, DevMap param) throws UserException {
        String mbrId = "";
        mbrId = param.getString("bnMbrId");

        Date uploadDate = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String uploadDateStr = sdf.format(uploadDate);

        String storFilNm = "SHOP_" + uploadDateStr + "_" + UuidUtil.getUuidOnlyString(); // 저장파일명
        String origFilNm = multipartFile.getOriginalFilename(); // 원시파일명
        String filTyp = multipartFile.getContentType();

        if(origFilNm.getBytes().length > 50) {
            throw new UserException("파일명의 길이가 50Byte를 초과할 수 없습니다.");
        }

        String filExtNm = origFilNm.substring(origFilNm.lastIndexOf(".")); // 파일 확장자
        String filStorPthTxt = settingProperties.getFileStorePath(); // 파일 저장경로
        long filSizNo = multipartFile.getSize(); // 파일크기
        if (filSizNo > 1000000) {
            throw new UserException("파일크기가 10MB를 초과할 수 없습니다.");
        }

        File file = new File(filStorPthTxt + storFilNm);

        try {
            multipartFile.transferTo(file);
        } catch (IllegalStateException e) {
            throw new UserException("파일저장중 오류가 발생했습니다. 운영자에게 문의바랍니다.");
        } catch (IOException e) {
            throw new UserException("파일저장중 오류가 발생했습니다. 운영자에게 문의바랍니다.");
        }

        DevMap fileInfo = new DevMap();
        fileInfo.put("storFilNm", storFilNm);
        fileInfo.put("filExtNm", filExtNm);
        fileInfo.put("filStorPthTxt", filStorPthTxt);
        fileInfo.put("origFilNm", origFilNm);
        fileInfo.put("filSizNo", filSizNo);
        fileInfo.put("filTyp", filTyp);
        fileInfo.put("inpMbrId", mbrId);

        // 파일 업로드 정보 DB저장
        cmmnDao.insert("sellerweb.common.insertFileDetail", fileInfo);

        return fileInfo;
    }

    /**
     * 이미지 태그의 src에 소스정보를 제공한다.
     * @param response
     * @param fileName
     * @throws UserException
     */
    public void imageSrc(HttpServletResponse response, String fileName) throws UserException {
//        String mimeType = mimeTypeParam;
        String fileStorePath = settingProperties.getFileStorePath();

        File file = new File(fileStorePath + filePathBlackList(fileName));
        if(!file.exists() || !file.isFile()) {
            throw new UserException("이미지파일이 존재하지 않습니다.");
        }

        byte[] b = new byte[BUFFER_SIZE];

        String mimeType = cmmnDao.selectOne("sellerweb.common.selectFileType", fileName);
        if(mimeType == null || mimeType.equals("")) {
            mimeType = "application/octet-stream;";
        }
        response.setContentType(removeCRLF(mimeType));
        response.setHeader("Content-Disposition", "filename=image;");

        try (BufferedInputStream fin = new BufferedInputStream(new FileInputStream(file));
             BufferedOutputStream outs = new BufferedOutputStream(response.getOutputStream())) {
            int read = 0;

            while (true) {
                read = fin.read(b);
                if (read == -1) {
                    break;
                }
                outs.write(b, 0, read);
            }
        } catch (Exception e) {
            log.error(CmmnUtil.getExceptionMsg(e));
            throw new UserException("이미지파일 로딩중 오류가 발생했습니다.");
        }
    }

    /**
     * 파일경로와 이름을 생성할 때 외부 입력값을 사용하는 경우,
     * 정해진 경로 이외의 디렉터리와 파일에 접근할 수 없도록 처리하고
     * 외부 입력값에 대해  replaceAll()등의 메소드를 사용하여
     * 의도하지 않은 경로로의 접근을 허용하는 위험 문자열(",/,\,.. 등)을 제고하는 필터링을 수행
     * @param value
     * @return
     */
    public static String filePathBlackList(String value) {
        String returnValue = value;
        if (returnValue == null || returnValue.trim().equals("")) {
            return "";
        }

        returnValue = returnValue.replaceAll("/", "");
        returnValue = returnValue.replaceAll("\\\\", "");

        return returnValue;
    }

    /**
     * 외부에서 입력된 파라미터를 HTTP 응답헤더에 포함시킬 경우 CR, LF 등의 개행문자 제거
     * @param parameter
     * @return
     */
    public static String removeCRLF(String parameter) {
        return parameter.replaceAll("\r", "").replaceAll("\n", "");
    }

}
