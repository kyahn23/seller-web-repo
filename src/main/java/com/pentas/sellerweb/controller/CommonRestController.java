package com.pentas.sellerweb.controller;

import com.pentas.sellerweb.common.exception.UserException;
import com.pentas.sellerweb.common.module.util.DevMap;
import com.pentas.sellerweb.common.module.util.json.JsonUtil;
import com.pentas.sellerweb.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
public class CommonRestController {

    @Autowired
    CommonService commonService;

    /**
     * 멤버 ID (이메일) 중복 확인 (return 객체에 중복 여부)
     * @param request
     * @param param
     * @return
     */
    @PostMapping("/member/checkId")
    public DevMap checkMemberId(HttpServletRequest request, @RequestBody DevMap param) {
        String chkMbrId = commonService.checkMemberId(param);

        DevMap rslt = new DevMap();
        rslt.put("chkMbrId", chkMbrId);
        return rslt;
    }

    /**
     * 마스터 회원 비밀번호 재설정
     * @param request
     * @param param
     * @return
     */
    @PostMapping("/member/resetMstPw")
    public DevMap resetMstPw(HttpServletRequest request, @RequestBody DevMap param) {
        commonService.updateMstPwInit(param);

        DevMap rslt = new DevMap();
        rslt.put("rsltStat", "SUCC");
        return rslt;
    }

    /**
     * 마스터 회원 가입
     * @param param
     * @return
     */
    @PostMapping("/member/newMstAcc")
    public DevMap newMstAcc(@RequestBody DevMap param) {
        commonService.addMstAcc(param);

        DevMap rslt = new DevMap();
        rslt.put("rsltStat", "SUCC");
        return rslt;
    }

    /**
     * 이미지 업로드 (return 객체에 새 파일명 filNm 포함)
     * @param request
     * @param multipartRequest
     * @return
     * @throws IOException
     * @throws IllegalStateException
     * @throws UserException
     */
    @PostMapping("/upload/image")
    public DevMap uploadImage(HttpServletRequest request, MultipartRequest multipartRequest) throws IOException, IllegalStateException, UserException {
        String mbrId = (String) request.getSession().getAttribute("mbrId");
        String paramStr = request.getParameter("param");
        DevMap param = JsonUtil.fromJsonStr(DevMap.class, paramStr);
        param.put("mbrId", mbrId);

        List<MultipartFile> multipartFileList = multipartRequest.getFiles("fileList");
        MultipartFile multipartFile = multipartFileList.get(0);
        DevMap fileProcRslt = commonService.uploadFile(multipartFile, param);

        DevMap rslt = new DevMap();
        rslt.put("filNm", fileProcRslt.getString("storFilNm"));
        rslt.put("rsltStat", "SUCC");
        return rslt;
    }

    /**
     * 이미지 태그의 src에 소스정보를 제공한다.
     * @param request
     * @param response
     * @throws UserException
     */
    @RequestMapping("/imageSrc")
    public void downloadImage(HttpServletRequest request, HttpServletResponse response) throws UserException {
        String fileName = request.getParameter("fileName");
        commonService.imageSrc(response, fileName);
    }

    /**
     * 파일 다운로드
     * @param request
     * @param response
     * @throws UserException
     */
    @RequestMapping("/downloadFile")
    public void downloadFile(HttpServletRequest request, HttpServletResponse response) throws UserException {
        String fileName = request.getParameter("fileName");
        commonService.downloadFile(response, fileName);
    }

}
