package com.pentas.sellerweb.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pentas.sellerweb.common.exception.UserException;
import com.pentas.sellerweb.common.module.util.DevMap;
import com.pentas.sellerweb.common.module.util.json.JsonUtil;
import com.pentas.sellerweb.service.CommonService;
import com.pentas.sellerweb.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ShopRestController {

    @Autowired
    ShopService shopService;

    @Autowired
    CommonService commonService;

    /**
     * 매장정보 조회
     * @param request
     * @param param
     * @return rslt
     */
    @PostMapping("/shop/getShopInfo")
    public DevMap getShopInfo(HttpServletRequest request, @RequestBody DevMap param) {
        HttpSession session = request.getSession();
        String bnMbrId = (String) session.getAttribute("bnMbrId");
        param.put("bnMbrId", bnMbrId);

        DevMap rslt = new DevMap();
        rslt = shopService.getShopInfo(param);
        return rslt;
    }

    /**
     * 매장정보 수정
     * @param request
     * @param param
     * @return rslt
     */
    @PostMapping("/shop/modiShopInfo")
    public DevMap modiShopInfo(HttpServletRequest request, @RequestBody DevMap param) throws IllegalStateException, UserException {
        HttpSession session = request.getSession();
        String bnMbrId = (String) session.getAttribute("bnMbrId");
        param.put("mbrId", bnMbrId);
        shopService.modiShopInfo(param);

        DevMap rslt = new DevMap();
        rslt.put("rsltStat", "SUCC");
        return rslt;
    }

    /**
     * 직원정보 조회
     * @param request
     * @param param
     * @return rslt
     */
    @PostMapping("/shop/getEmpList")
    public DevMap getEmpList(HttpServletRequest request, @RequestBody DevMap param) {
        HttpSession session = request.getSession();
        String bnMbrId = (String) session.getAttribute("bnMbrId");
        param.put("bnMbrId", bnMbrId);

        DevMap rslt = new DevMap();
        List<DevMap> resultList = null;
        resultList = shopService.getEmpList(param);
        rslt.put("empList", resultList);
        return rslt;
    }

    /**
     * 직원 비밀번호 재발급
     * @param request
     * @param param
     * @return rslt
     */
    @PostMapping("/shop/modiEmpPwd")
    public DevMap modiEmpPwd(HttpServletRequest request, @RequestBody DevMap param) {
        HttpSession session = request.getSession();
        String bnMbrId = (String) session.getAttribute("bnMbrId");
        param.put("amdMbrId", bnMbrId);

        shopService.modiEmpPwd(param);

        DevMap rslt = new DevMap();
        rslt.put("rsltStat", "SUCC");
        return rslt;
    }

    /**
     * 직원 권한 수정
     * @param request
     * @param param
     * @return
     */
    @PostMapping("/shop/modiEmpPrms")
    public DevMap modiEmpPrms(HttpServletRequest request, @RequestBody DevMap param) {
        HttpSession session = request.getSession();
        String bnMbrId = (String) session.getAttribute("bnMbrId");
        param.put("amdMbrId", bnMbrId);

        shopService.modiEmpPrms(param);

        DevMap rslt = new DevMap();
        rslt.put("rsltStat", "SUCC");
        return rslt;
    }

    /**
     * 매장 공지사항 글 작성
     * @param request
     * @param multipartRequest
     * @return
     * @throws IOException
     * @throws IllegalStateException
     * @throws UserException
     */
    @PostMapping("/shop/addBnBrd")
    public DevMap addBnBrd(HttpServletRequest request, MultipartRequest multipartRequest) throws IOException, IllegalStateException, UserException {
        HttpSession session = request.getSession();
        String bnMbrId = (String) session.getAttribute("bnMbrId");

        String paramStr = request.getParameter("param");
        DevMap param = JsonUtil.fromJsonStr(DevMap.class, paramStr);
        param.put("bnMbrId", bnMbrId);

        DevMap fileUploadParam = new DevMap();
        fileUploadParam.put("bnMbrId", bnMbrId);
        fileUploadParam.put("fileTgt", "board");

        List<DevMap> insFileInfoList = new ArrayList<>();
        List<MultipartFile> multipartFileList = multipartRequest.getFiles("fileList");
        for (MultipartFile multipartFile : multipartFileList) {
            DevMap fileProcRslt = new DevMap();
            fileProcRslt = commonService.uploadFile(multipartFile, fileUploadParam);
            insFileInfoList.add(fileProcRslt);
        }

        int filCnt = 0;
        for (DevMap insFileInfo : insFileInfoList) {
            filCnt++;
            param.put("brdAttFile" + filCnt, insFileInfo.getString("storFilNm"));
        }

        shopService.addBnBrd(param);

        DevMap rslt = new DevMap();
        rslt.put("rsltStat", "SUCC");
        return rslt;
    }

    /**
     * 매장 공지사항 목록 조회
     * @param request
     * @param param
     * @return
     */
    @PostMapping("/shop/getBnBrdList")
    public DevMap getBnBrdList(HttpServletRequest request, @RequestBody DevMap param) {
        HttpSession session = request.getSession();
        String bnMbrId = (String) session.getAttribute("bnMbrId");
        param.put("bnMbrId", bnMbrId);

        DevMap rslt = new DevMap();
        List<DevMap> bnBrdList = null;

        bnBrdList = shopService.getBnBrdList(param);
        rslt.put("bnBrdList", bnBrdList);
        return rslt;
    }

    /**
     * 매장 공지사항 게시여부 숨김으로 변경
     * @param request
     * @param param
     * @return
     */
    @PostMapping("/shop/modiBnBrdHide")
    public DevMap modiBnBrdHide(HttpServletRequest request, @RequestBody DevMap param) {
        HttpSession session = request.getSession();
        String bnMbrId = (String) session.getAttribute("bnMbrId");
        param.put("bnMbrId", bnMbrId);

        shopService.modiBnBrdHide(param);

        DevMap rslt = new DevMap();
        rslt.put("rsltStat", "SUCC");
        return rslt;
    }

    /**
     * 매장 공지사항 글 가져오기
     * @param request
     * @param param
     * @return
     */
    @PostMapping("/shop/getBnBrdOne")
    public DevMap getBnBrdOne(HttpServletRequest request, @RequestBody DevMap param) {
        HttpSession session = request.getSession();
        String bnMbrId = (String) session.getAttribute("bnMbrId");
        param.put("bnMbrId", bnMbrId);

        DevMap rslt = new DevMap();
        rslt = shopService.getBnBrdOne(param);
        return rslt;
    }

    /**
     * 매장 공자사항 글 수정
     * @param request
     * @param multipartRequest
     * @return
     * @throws IOException
     * @throws IllegalStateException
     * @throws UserException
     */
    @PostMapping("/shop/modiBnBrdOne")
    public DevMap modiBnBrdOne(HttpServletRequest request, MultipartRequest multipartRequest) throws IOException, IllegalStateException, UserException {
        HttpSession session = request.getSession();
        String bnMbrId = (String) session.getAttribute("bnMbrId");

        String paramStr = request.getParameter("param");
        DevMap param = JsonUtil.fromJsonStr(DevMap.class, paramStr);
        param.put("bnMbrId", bnMbrId);

        DevMap fileUploadParam = new DevMap();
        fileUploadParam.put("bnMbrId", bnMbrId);
        fileUploadParam.put("fileTgt", "board");

        List<DevMap> insFileInfoList = new ArrayList<>();
        List<MultipartFile> multipartFileList = multipartRequest.getFiles("fileList");
        for (MultipartFile multipartFile : multipartFileList) {
            DevMap fileProcRslt = new DevMap();
            fileProcRslt = commonService.uploadFile(multipartFile, fileUploadParam);
            fileProcRslt.put("filStat", "NEW");
            insFileInfoList.add(fileProcRslt);
        }

        ObjectMapper mapper = new ObjectMapper();
        List<DevMap> newFileInfo =  mapper.convertValue(param.get("brdOneNewFileInfo"), new TypeReference<List<DevMap>>() {});
        int filCnt = 0;
        for (DevMap fileInfo : newFileInfo) {
            String filStat = fileInfo.getString("filStat");
            if (filStat.equals("DEL")) {
                commonService.updateFileDelYn(fileInfo);
            } else if (filStat.equals("CUR")) {
                filCnt++;
                param.put("brdAttFile" + filCnt, fileInfo.getString("storFilNm"));
            }
        }
        for (DevMap insFileInfo : insFileInfoList) {
            filCnt++;
            param.put("brdAttFile" + filCnt, insFileInfo.getString("storFilNm"));
        }

        if (filCnt == 1) {
            param.put("brdAttFile2", null);
        }

        shopService.modiBnBrdOne(param);

        DevMap rslt = new DevMap();
        rslt.put("rsltStat", "SUCC");
        return rslt;
    }
}
