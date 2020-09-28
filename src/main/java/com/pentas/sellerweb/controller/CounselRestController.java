package com.pentas.sellerweb.controller;

import com.pentas.sellerweb.common.module.mybatis.paginator.domain.PageList;
import com.pentas.sellerweb.common.module.util.DevMap;
import com.pentas.sellerweb.service.CounselService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class CounselRestController {

    @Autowired
    CounselService counselService;


    /**
     * 상담접수현황 목록
     * @param param
     * @return
     */
    @PostMapping("/service/getCounselList")
    public DevMap getCounselList(@RequestBody DevMap param, HttpServletRequest request) {
        //        세션에서 회원아이디 가져오기
        HttpSession session = request.getSession();
        String loginMbrId = (String) session.getAttribute("bnMbrId");
        param.put("LOGIN_MBR_ID", loginMbrId);
        param.put("CALL_ST_CD", "R");   // 등록된 상담만 보기위해

        DevMap rslt = new DevMap();
        PageList<DevMap> listPage = counselService.counselList(param);

        rslt.put("counselList", listPage);
        rslt.put("pageInfo", listPage.getPaginator());
        return rslt;
    }

    /**
     * 선택한 상담접수건의 현재 판매정책정보
     * @param param
     * @return
     */
    @PostMapping("/service/getCurrPolicy")
    public DevMap getCurrPolicy(@RequestBody DevMap param, HttpServletRequest request) {
//        세션에서 회원아이디 가져오기
        HttpSession session = request.getSession();
        String loginMbrId = (String) session.getAttribute("bnMbrId");
        param.put("LOGIN_MBR_ID", loginMbrId);

        DevMap rslt = new DevMap();
        rslt = counselService.getCurrPolicy(param);
        return rslt;
    }


    /**
     * 방문예정 상담목록 가져오기
     * @param param
     * @return
     */
    @PostMapping("/service/getVisitCounselList")
    public DevMap getVisitCounselList(@RequestBody DevMap param, HttpServletRequest request) {
        //        세션에서 회원아이디 가져오기
        HttpSession session = request.getSession();
        String loginMbrId = (String) session.getAttribute("bnMbrId");
        param.put("LOGIN_MBR_ID", loginMbrId);

        DevMap rslt = new DevMap();
        param.put("CALL_ST_CD", "P");   // 방문예정(상담상태 진행중-P) 상담만 보기위해

        PageList<DevMap> listPage = counselService.counselList(param);

        rslt.put("counselList", listPage);
        rslt.put("pageInfo", listPage.getPaginator());
        return rslt;
    }

    /**
     * 업체직원아이디 가져오기
     * @param param
     * @return
     */
    @PostMapping("/service/bnMbrList")
    public List<DevMap> bnMbrList(@RequestBody DevMap param, HttpServletRequest request) {
        //        세션에서 회원아이디 가져오기
        HttpSession session = request.getSession();
        String loginMbrId = (String) session.getAttribute("bnMbrId");
        param.put("LOGIN_MBR_ID", loginMbrId);

        List<DevMap> resultList = null;
        resultList = counselService.bnMbrList(param);

        return resultList;
    }

    /**
     * 상담관리현황페이지
     * @param param
     * @return
     */
    @PostMapping("/service/getAllCounselList")
    public DevMap getAllCounselList(@RequestBody DevMap param, HttpServletRequest request) {
        //        세션에서 회원아이디 가져오기
        HttpSession session = request.getSession();
        String loginMbrId = (String) session.getAttribute("bnMbrId");
        param.put("LOGIN_MBR_ID", loginMbrId);

        DevMap rslt = new DevMap();
//        param.put("CALL_ST_CD", "all");

        PageList<DevMap> listPage = counselService.counselList(param);

        rslt.put("counselList", listPage);
        rslt.put("pageInfo", listPage.getPaginator());
        return rslt;
    }

    /**
     * 상담결과저장
     * @param param
     * @return
     */
    @PostMapping("/service/saveCounsel")
    public DevMap saveCounsel(@RequestBody DevMap param, HttpServletRequest request) {
        //        세션에서 회원아이디 가져오기
        HttpSession session = request.getSession();
        String loginMbrId = (String) session.getAttribute("bnMbrId");
        param.put("LOGIN_MBR_ID", loginMbrId);
        param.put("CALL_ST_CD", "P");   // 상담상태 진행중으로 변경

        DevMap rslt = new DevMap();
        counselService.saveCounsel(param);

        rslt.put("succ", "Y");
        return rslt;
    }

    /**
     * 방문예정일시 변경
     * @param param
     * @return
     */
    @PostMapping("/service/modiVisitDt")
    public DevMap modiVisitDt(@RequestBody DevMap param, HttpServletRequest request) {
        //        세션에서 회원아이디 가져오기
        HttpSession session = request.getSession();
        String loginMbrId = (String) session.getAttribute("bnMbrId");
        param.put("LOGIN_MBR_ID", loginMbrId);

        DevMap rslt = new DevMap();
        counselService.modiVisitDt(param);

        rslt.put("succ", "Y");
        return rslt;
    }

    @PostMapping("/service/useAllCarrMntRt")
    public DevMap useAllCarrMntRt(@RequestBody DevMap param, HttpServletRequest request) {
        //        세션에서 회원아이디 가져오기
        HttpSession session = request.getSession();
        String loginMbrId = (String) session.getAttribute("bnMbrId");
        param.put("LOGIN_MBR_ID", loginMbrId);

        DevMap rslt = new DevMap();
        List<DevMap> resultList = null;

        resultList = counselService.useAllCarrMntRt(param);
        rslt.put("mntrtList", resultList);
        return rslt;
    }

    @PostMapping("/service/allDeviceListByCarr")
    public DevMap allDeviceListByCarr(@RequestBody DevMap param) {
        DevMap rslt = new DevMap();
        List<DevMap> resultList = null;

        resultList = counselService.allDeviceListByCarr(param);
        rslt.put("deviceList", resultList);
        return rslt;
    }

    /**
     * 개통결과 등록
     * @param param
     * @param request
     * @return
     */
    @PostMapping("/service/registerRslt")
    public DevMap registerRslt(@RequestBody DevMap param, HttpServletRequest request) {
        //        세션에서 회원아이디 가져오기
        HttpSession session = request.getSession();
        String loginMbrId = (String) session.getAttribute("bnMbrId");
        param.put("LOGIN_MBR_ID", loginMbrId);

        DevMap rslt = new DevMap();

        counselService.registerRslt(param);

        // 상담 종료 확인
        String callStatus = param.getString("callStCd");
        if (callStatus.equals("T")) {
            // 클라이언트 회원 마케팅 동의 여부 확인 후 마케팅 대상 추가
            String clientMarketing = counselService.checkClientMarketing(param);
            if (clientMarketing.equals("Y")) {
                counselService.newMarketingOne(param);
            }
        }

        rslt.put("succ", "Y");
        return rslt;
    }

    /**
     * 마케팅 대상 목록 조회
     * @param param
     * @return
     */
    @PostMapping("/service/getMarketingList")
    public DevMap getMarketingList(HttpServletRequest request, @RequestBody DevMap param) {
        HttpSession session = request.getSession();
        String bnMbrId = (String) session.getAttribute("bnMbrId");
        param.put("bnMbrId", bnMbrId);

        DevMap rslt = new DevMap();

        PageList<DevMap> listPage = counselService.marketingList(param);

        rslt.put("mktList", listPage);
        rslt.put("pageInfo", listPage.getPaginator());
        return rslt;
    }

    /**
     * 마케팅 결과 상세 조회
     * @param param
     * @return
     */
    @PostMapping("/service/getMarketingOne")
    public DevMap getMarketingOne(@RequestBody DevMap param) {
        DevMap rslt = new DevMap();
        DevMap marketingOne = counselService.marketingOne(param);
        String pnMkrOne = "";
        if (marketingOne.getString("pnMdl") != null) {
            pnMkrOne = counselService.pnMkrOne(marketingOne.getString("pnMdl"));
            marketingOne.put("pnMkr", pnMkrOne);
        }
        rslt.put("mktOne", marketingOne);
        return rslt;
    }

    /**
     * 요금제 목록 조회
     * @param request
     * @param param
     * @return
     */
    @PostMapping("/service/getMonthlyRate")
    public DevMap getMonthlyRate(HttpServletRequest request, @RequestBody DevMap param) {
        HttpSession session = request.getSession();
        String bnMbrId = (String) session.getAttribute("bnMbrId");
        param.put("bnMbrId", bnMbrId);

        DevMap rslt = new DevMap();
        List<DevMap> monthlyRate = counselService.monthlyRateList(param);
        rslt.put("monthlyRate", monthlyRate);
        return rslt;
    }

    /**
     * 제조사 목록 조회
     * @param param
     * @return
     */
    @PostMapping("/service/getPnMkr")
    public DevMap getPnMkr(@RequestBody DevMap param) {
        DevMap rslt = new DevMap();
        List<String> pnMkr = counselService.pnMkr(param);
        rslt.put("pnMkr", pnMkr);
        return rslt;
    }

    /**
     * 기기 목록 조회
     * @param request
     * @param param
     * @return
     */
    @PostMapping("/service/getDeviceList")
    public DevMap getDeviceList(HttpServletRequest request, @RequestBody DevMap param) {
        DevMap rslt = new DevMap();
        List<DevMap> deviceList = counselService.deviceList(param);
        rslt.put("deviceList", deviceList);
        return rslt;
    }

    /**
     * 마케팅 상세 정보 저장
     * @param param
     * @return
     */
    @PostMapping("/service/saveMarketingOne")
    public DevMap saveMarketingOne(@RequestBody DevMap param) {
        counselService.saveMarketingOne(param);

        DevMap rslt = new DevMap();
        rslt.put("rsltStat", "SUCC");
        return rslt;
    }

    /**
     * 마케팅 상세 정보 저장 (신규, 별도 개통)
     * @param param
     * @return
     */
    @PostMapping("/service/saveMarketingNew")
    public DevMap saveMarketingNew(HttpServletRequest request, @RequestBody DevMap param) {
        HttpSession session = request.getSession();
        String bnMbrId = (String) session.getAttribute("bnMbrId");
        param.put("bnMbrId", bnMbrId);

        counselService.saveMarketingNew(param);

        DevMap rslt = new DevMap();
        rslt.put("rsltStat", "SUCC");
        return rslt;
    }

}
