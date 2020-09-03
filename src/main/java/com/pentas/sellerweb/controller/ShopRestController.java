package com.pentas.sellerweb.controller;

import com.pentas.sellerweb.common.exception.UserException;
import com.pentas.sellerweb.common.module.util.DevMap;
import com.pentas.sellerweb.common.module.util.json.JsonUtil;
import com.pentas.sellerweb.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@RestController
public class ShopRestController {

    @Autowired
    ShopService shopService;

    /**
     * 매장정보 조회
     * @param request
     * @param param
     * @return
     */
    @PostMapping("/shop/getShopInfo")
    public DevMap getShopInfo(HttpServletRequest request, @RequestBody DevMap param) {
        DevMap rslt = new DevMap();
        rslt = shopService.getShopInfo(param);
        return rslt;
    }

    /**
     * 매장정보 수정
     * @param request
     * @param param
     * @return
     * @throws UserException
     */
    @PostMapping("/shop/modiShopInfo")
    public DevMap modiShopInfo(HttpServletRequest request, @RequestBody DevMap param) throws IllegalStateException, UserException {
        String mbrId = (String) request.getSession().getAttribute("mbrId");
        param.put("mbrId", mbrId);
        shopService.modiShopInfo(param);

        DevMap rslt = new DevMap();
        rslt.put("rsltStat", "SUCC");
        return rslt;
    }

    /**
     * 직원정보 조회
     * @param request
     * @param param
     * @return
     */
    @PostMapping("/shop/getEmpList")
    public DevMap getEmpList(HttpServletRequest request, @RequestBody DevMap param) {
        DevMap rslt = new DevMap();
        List<DevMap> resultList = null;
        resultList = shopService.getEmpList(param);
        rslt.put("empList", resultList);
        return rslt;
    }

    /**
     * 직원정보 추가
     * @param request
     * @param param
     * @return
     * @throws UserException
     */
    @PostMapping("/shop/addEmpInfo")
    public DevMap addEmpInfo(HttpServletRequest request, @RequestBody DevMap param) throws UserException {
        shopService.addEmpInfo(param);

        DevMap rslt = new DevMap();
        rslt.put("rsltStat", "SUCC");
        return rslt;
    }

    /**
     * 직원 퇴사처리 (update)
     * @param request
     * @param param
     * @return
     * @throws UserException
     */
    @PostMapping("/shop/modiEmpDeact")
    public DevMap modiEmpDeact(HttpServletRequest request, @RequestBody DevMap param) throws UserException {
        shopService.modiEmpDeact(param);

        DevMap rslt = new DevMap();
        rslt.put("rsltStat", "SUCC");
        return rslt;
    }

}
