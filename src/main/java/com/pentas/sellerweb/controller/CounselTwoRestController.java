package com.pentas.sellerweb.controller;

import com.pentas.sellerweb.common.module.mybatis.paginator.domain.PageList;
import com.pentas.sellerweb.common.module.util.DevMap;
import com.pentas.sellerweb.service.CounselTwoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class CounselTwoRestController {

    @Autowired
    CounselTwoService counselTwoService;

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

        PageList<DevMap> listPage = counselTwoService.marketingList(param);

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
        DevMap marketingOne = counselTwoService.marketingOne(param);
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
        List<DevMap> monthlyRate = counselTwoService.monthlyRateList(param);
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
        List<String> pnMkr = counselTwoService.pnMkr(param);
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
        List<DevMap> deviceList = counselTwoService.deviceList(param);
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
        counselTwoService.saveMarketingOne(param);

        DevMap rslt = new DevMap();
        rslt.put("rsltStat", "SUCC");
        return rslt;
    }

}
