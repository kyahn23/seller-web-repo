package com.pentas.sellerweb.service;

import com.pentas.sellerweb.common.conf.properties.EmailProperties;
import com.pentas.sellerweb.common.conf.properties.SettingProperties;
import com.pentas.sellerweb.common.dao.CmmnDao;
import com.pentas.sellerweb.common.exception.UserException;
import com.pentas.sellerweb.common.module.util.CmmnUtil;
import com.pentas.sellerweb.common.module.util.DevMap;
import com.pentas.sellerweb.common.module.util.email.EmailUtil;
import com.pentas.sellerweb.common.module.util.uuid.UuidUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

@Service
public class CommonService {
    private static final Logger log = LoggerFactory.getLogger(CommonService.class);

    private static final int BUFFER_SIZE = 1000000;

    @Autowired
    SettingProperties settingProperties;

    @Autowired
    EmailProperties emailProperties;

    @Autowired
    CmmnDao cmmnDao;


    /**
     * 멤버 ID (이메일) 중복 확인
     * @param param
     * @return
     */
    public String checkMemberId(DevMap param) {
        return cmmnDao.selectOne("sellerweb.common.selectCntMbrId", param);
    }


    /**
     * 비밀번호 오류 횟수 초기화
     * @param bnMbrId
     */
    public void updatePwerrCntZero(String bnMbrId) { cmmnDao.update("sellerweb.common.updatePwerrCntZero", bnMbrId); }

    /**
     * 비밀번호 오류 횟수 추가
     * @param bnMbrId
     */
    public void updatePwerrCntPlus(String bnMbrId) { cmmnDao.update("sellerweb.common.updatePwerrCntPlus", bnMbrId); }

    /**
     * 로그인 가능여부 가능으로 변경
     * @param bnMbrId
     */
    public void updateLginAvlY(String bnMbrId) { cmmnDao.update("sellerweb.common.updateLginAvlY", bnMbrId); }

    /**
     * 로그인 가능여부 불가로 변경
     * @param bnMbrId
     */
    public void updateLginAvlN(String bnMbrId) { cmmnDao.update("sellerweb.common.updateLginAvlN", bnMbrId); }

    /**
     * 마스터 회원 비밀번호 변경
     * @param param
     */
    public void updateMstPwInit(DevMap param) {
        String mstMbrId = (String) param.get("mstMbrId");
        String ownerNm = (String) param.get("ownerNm");

        String hashPwd = generateTempPwd(mstMbrId, ownerNm);
        param.put("tempPwd", hashPwd);

        cmmnDao.update("sellerweb.common.updateMstPwInit", param);
    }

    /**
     * 초기화 비밀번호 변경
     * @param param
     */
    public void updateMbrPw(DevMap param) {
        cmmnDao.update("sellerweb.common.updateMbrPw", param);
    }

    /**
     * 마스터 회원 가입
     * @param param
     */
    public void addMstAcc(DevMap param) {
        cmmnDao.insert("sellerweb.common.insertNewBnAcc", param);

        String mstMbrId = (String) param.get("mstMbrId");
        String ownerNm = (String) param.get("ownerNm");

        String hashPwd = generateTempPwd(mstMbrId, ownerNm);
        param.put("tempPwd", hashPwd);

        cmmnDao.insert("sellerweb.common.insertNewMstAcc", param);
    }

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
        if (filSizNo > 100000000) {
            throw new UserException("파일크기가 100MB를 초과할 수 없습니다.");
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
     * 파일 정보 조회
     * @param param : 파일저장명
     * @return
     */
    public DevMap getFileInfo(String param) {
        return cmmnDao.selectOne("sellerweb.common.selectFileInfo", param);
    }

    /**
     * 업로드한 파일 삭제 처리 (update)
     * @param param
     * @throws UserException
     */
    public void updateFileDelYn(DevMap param) throws UserException {
        cmmnDao.update("sellerweb.common.updateFileDelYn", param);
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

        DevMap fileInfo = getFileInfo(fileName);
        String mimeType = fileInfo.getString("filTyp");
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
     * 파일 다운로드
     * @param response
     * @param fileName
     * @throws UserException
     */
    public void downloadFile(HttpServletResponse response, String fileName) throws UserException {
        DevMap fileInfo = getFileInfo(fileName);
        if (fileInfo == null) {
            throw new UserException("파일정보가 존재하지 않습니다.");
        }
        String origFilNm = fileInfo.getString("origFilNm");
        String storFilNm = fileInfo.getString("storFilNm");
        String filTyp = fileInfo.getString("filTyp");

        String fileStorePath = settingProperties.getFileStorePath();

        File file = new File(fileStorePath + filePathBlackList(storFilNm));

        if(!file.exists() || !file.isFile()) {
            throw new UserException("파일이 존재하지 않습니다.");
        }

        byte[] bufByte = new byte[BUFFER_SIZE];

        try {
            origFilNm = URLEncoder.encode(origFilNm, "UTF-8");
        } catch (UnsupportedEncodingException e1) {
            throw new UserException("파일 다운로드 중 오류가 발생했습니다.");
        }

        response.setContentType(filTyp);
        StringBuffer strBuf = new StringBuffer();
        strBuf.append("attachment; filename=\"");
        strBuf.append(origFilNm);
        strBuf.append("\";");
        response.setHeader("Content-Disposition", strBuf.toString());
        response.setHeader("Content-Transfer-Encoding", "binary");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "0");

        try (BufferedInputStream bufIS = new BufferedInputStream(new FileInputStream(file));
            BufferedOutputStream bufOS = new BufferedOutputStream(response.getOutputStream())
        ){
            int read = 0;

            while (true) {
                read = bufIS.read(bufByte);
                if (read == -1) {
                    break;
                }
                bufOS.write(bufByte, 0, read);
            }
        } catch (Exception e) {
            throw new UserException("파일 로딩 중 오류가 발생했습니다.");
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

    /**
     * 임시 비밀번호 생성 후 이메일 발송
     * @param mstMbrId
     * @param ownerNm
     * @return
     */
    private String generateTempPwd(String mstMbrId, String ownerNm) {
        String tempPwd = (new Random().nextInt(900000) + 100000) + "";
        String hashPwd = CmmnUtil.encryptSHA256(tempPwd.toString());

        EmailUtil.sendMailAuthSSL(
                emailProperties.getSmtpHost(),
                emailProperties.getSmtpPort(),
                emailProperties.getSmtpUser(),
                emailProperties.getSmtpPassword(),
                "[Pentaworks Service] 임시 비밀번호 발급 안내",
                "<html><p>" + ownerNm + "님의 임시 비밀번호가 발급되었습니다.<br>" +
                        "임시 비밀번호로 로그인 후 새 비밀번호로 변경해주세요.<br></p>" +
                        "<h4>임시 비밀번호 : " + tempPwd + "</h4></html>",
                mstMbrId + "",
                emailProperties.getFromEmail(),
                emailProperties.getFromName()
        );

        return hashPwd;
    }

}
