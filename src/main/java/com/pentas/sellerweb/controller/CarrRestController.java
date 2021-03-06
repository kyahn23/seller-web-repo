package com.pentas.sellerweb.controller;

import com.pentas.sellerweb.common.module.util.DevMap;
import com.pentas.sellerweb.service.CarrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class CarrRestController {

    @Autowired
    CarrService carrService;

    /**
     * 요금제현황 가져오기
     *
     * @param param
     * @return
     */
    @PostMapping("/carr/getMntrtList")
    public DevMap getMntrtList(@RequestBody DevMap param, HttpServletRequest request) {
//        세션에서 회원아이디 가져오기
        HttpSession session = request.getSession();
        String mbrId = (String) session.getAttribute("bnMbrId");
        param.put("BN_MBR_ID", mbrId);      // 회원아이디

        DevMap rslt = new DevMap();
        List<DevMap> resultList = null;

        resultList = carrService.mntrtList(param);
        rslt.put("mntrtList", resultList);
        return rslt;
    }

    /**
     * 요금제 사용
     *
     * @param param
     * @return
     */
    @PostMapping("/carr/insertUseMnt")
    public DevMap insertUseMnt(@RequestBody List<DevMap> param, HttpServletRequest request) {
        //        세션에서 회원아이디 가져오기
        HttpSession session = request.getSession();
        String mbrId = (String) session.getAttribute("bnMbrId");
        DevMap rslt = new DevMap();
        for (DevMap list : param) {
            list.put("BN_MBR_ID", mbrId);      // 회원아이디
        }
        carrService.insertUseMnt(param);

        // 정상적으로 insert
        rslt.put("succ", "Y");
        return rslt;
    }

    /**
     * 요금제 미사용처리
     *
     * @param param
     * @return
     */
    @PostMapping("/carr/deleteUseMnt")
    public DevMap deleteUseMnt(@RequestBody List<DevMap> param, HttpServletRequest request) {
        //        세션에서 회원아이디 가져오기
        HttpSession session = request.getSession();
        String mbrId = (String) session.getAttribute("bnMbrId");
        DevMap rslt = new DevMap();
        for (DevMap list : param) {
            list.put("BN_MBR_ID", mbrId);      // 회원아이디
        }

        carrService.deleteUseMnt(param);
        rslt.put("succ", "Y");

        return rslt;
    }

    /**
     * 제품현황 리스트 가져오기
     *
     * @param param
     * @return
     */
    @PostMapping("/carr/getSellDeviceList")
    public DevMap getSellDeviceList(@RequestBody DevMap param, HttpServletRequest request) {
        //        세션에서 회원아이디 가져오기
        HttpSession session = request.getSession();
        String mbrId = (String) session.getAttribute("bnMbrId");
        param.put("BN_MBR_ID", mbrId);      // 회원아이디

        DevMap rslt = new DevMap();
        List<DevMap> resultList = null;

        resultList = carrService.getSellDeviceList(param);
        rslt.put("sellDeviceList", resultList);
        return rslt;
    }

    /**
     * 사용중인 요금제 가져오기
     *
     * @param param
     * @return
     */
    @PostMapping("/carr/getUseMntByDevice")
    public DevMap getUseMntByDevice(@RequestBody DevMap param, HttpServletRequest request) {
        //        세션에서 회원아이디 가져오기
        HttpSession session = request.getSession();
        String mbrId = (String) session.getAttribute("bnMbrId");
        param.put("BN_MBR_ID", mbrId);      // 회원아이디                               // 회원아이디

        DevMap rslt = new DevMap();
        List<DevMap> moveCarrList = null;
        moveCarrList = carrService.getmoveCarrList(param);
        List<DevMap> chgDeviceList = null;
        chgDeviceList = carrService.getchgDeviceList(param);
        List<DevMap> newSignUpList = null;
        newSignUpList = carrService.getnewSignUpList(param);

        rslt.put("moveCarrList", moveCarrList);
        rslt.put("chgDeviceList", chgDeviceList);
        rslt.put("newSignUpList", newSignUpList);

        return rslt;
    }

    /**
     * 제품현황-판매중지처리
     *
     * @param param
     * @return
     */
    @PostMapping("/carr/stopSaleDev")
    public DevMap stopSaleDevice(@RequestBody List<DevMap> param, HttpServletRequest request) {
        //        세션에서 회원아이디 가져오기
        HttpSession session = request.getSession();
        String mbrId = (String) session.getAttribute("bnMbrId");
        for (DevMap list : param) {
            list.put("BN_MBR_ID", mbrId);      // 회원아이디
        }

        DevMap rslt = new DevMap();
        carrService.stopSaleDevice(param);

        rslt.put("succ", "Y");
        return rslt;
    }

    /**
     * 판매정책별 저장
     *
     * @param saleType
     * @param param
     * @return
     */
    @PostMapping("/carr/saveSalePolicy/{saleType}")
    public DevMap saveSalePolicy(@PathVariable String saleType, @RequestBody List<DevMap> param, HttpServletRequest request) {
        //        세션에서 회원아이디 가져오기
        HttpSession session = request.getSession();
        String mbrId = (String) session.getAttribute("bnMbrId");
        for (DevMap list : param) {
            list.put("BN_MBR_ID", mbrId);      // 회원아이디
        }

        DevMap rslt = new DevMap();
        switch (saleType) {
            case "moveCarr":
                carrService.saveSalePolicyMoveCarr(param);
                break;
            case "chgDevice":
                carrService.saveSalePolicyChgDev(param);
                break;
            case "newSignUp":
                carrService.saveSalePolicyNewSignup(param);
                break;
        }

        rslt.put("succ", "Y");
        return rslt;
    }

}
