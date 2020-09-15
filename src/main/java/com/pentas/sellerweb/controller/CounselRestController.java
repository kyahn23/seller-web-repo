package com.pentas.sellerweb.controller;

import com.pentas.sellerweb.common.module.mybatis.paginator.domain.PageBounds;
import com.pentas.sellerweb.common.module.mybatis.paginator.domain.PageList;
import com.pentas.sellerweb.common.module.util.DevMap;
import com.pentas.sellerweb.service.CounselService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CounselRestController {

    @Autowired
    CounselService counselService;


    /**
     * 상담접수현황 목록
     *
     * @param param
     * @return
     */
    @PostMapping("/service/getCounselList")
    public DevMap getCounselList(@RequestBody DevMap param) {
        DevMap rslt = new DevMap();
        param.put("CALL_ST_CD", "R");   // 등록된 상담만 보기위해

        PageList<DevMap> listPage = counselService.counselList(param);

        rslt.put("counselList", listPage);
        rslt.put("pageInfo", listPage.getPaginator());
        return rslt;
    }

    /**
     * 선택한 상담접수건의 현재 판매정책정보
     *
     * @param param
     * @return
     */
    @PostMapping("/service/getCurrPolicy")
    public DevMap getCurrPolicy(@RequestBody DevMap param) {
        DevMap rslt = new DevMap();
        rslt = counselService.getCurrPolicy(param);
        return rslt;
    }


    /**
     * 방문예정 상담목록 가져오기
     *
     * @param param
     * @return
     */
    @PostMapping("/service/getVisitCounselList")
    public DevMap getVisitCounselList(@RequestBody DevMap param) {
        DevMap rslt = new DevMap();
        param.put("CALL_ST_CD", "P");   // 방문예정(상담상태 진행중-P) 상담만 보기위해

        PageList<DevMap> listPage = counselService.counselList(param);

        rslt.put("counselList", listPage);
        rslt.put("pageInfo", listPage.getPaginator());
        return rslt;
    }

    /**
     * 업체직원아이디 가져오기
     *
     * @param param
     * @return
     */
    @PostMapping("/service/bnMbrList")
    public List<DevMap> bnMbrList(@RequestBody DevMap param) {
        param.put("BN_NO", "202008250001");

        List<DevMap> resultList = null;
        resultList = counselService.bnMbrList(param);

        return resultList;
    }

    /**
     * 상담관리현황페이지 전체 목록
     *
     * @param param
     * @return
     */
    @PostMapping("/service/getAllCounselList")
    public DevMap getAllCounselList(@RequestBody DevMap param) {
        DevMap rslt = new DevMap();
        param.put("CALL_ST_CD", "all");   // 방문예정(상담상태 진행중-P) 상담만 보기위해

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
    public DevMap saveCounsel(@RequestBody DevMap param) {
        DevMap rslt = new DevMap();

        String bnMbrId = "qwerasdfzxcvqwerasdfzxcvqwerasdfzxcvqwer@naver.com";

        param.put("CALL_ST_CD", "P");   // 상담상태 진행중으로 변경
        param.put("BN_NO", "202008250001");
        param.put("BN_MBR_ID", bnMbrId);
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
    public DevMap modiVisitDt(@RequestBody DevMap param) {
        DevMap rslt = new DevMap();

        String bnMbrId = "qwerasdfzxcvqwerasdfzxcvqwerasdfzxcvqwer@naver.com";

        param.put("BN_NO", "202008250001");
        param.put("BN_MBR_ID", bnMbrId);
        counselService.modiVisitDt(param);

        rslt.put("succ", "Y");

        return rslt;
    }

    @PostMapping("/service/useAllCarrMntRt")
    public DevMap useAllCarrMntRt(@RequestBody DevMap param){
        DevMap rslt = new DevMap();
        List<DevMap> resultList = null;

        resultList = counselService.useAllCarrMntRt(param);

        rslt.put("mntrtList",resultList);
        return rslt;
    }
}
