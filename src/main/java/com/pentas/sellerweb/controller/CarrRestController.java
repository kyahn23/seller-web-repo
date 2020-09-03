package com.pentas.sellerweb.controller;

import com.pentas.sellerweb.common.module.util.DevMap;
import com.pentas.sellerweb.service.CarrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.List;

@RestController
public class CarrRestController {

    @Autowired
    CarrService carrService;

    /**
     * 요금제 현황 목록 가져오기
     * @param param
     * @return
     */
    @PostMapping("/carr/getMntrtList")
    public DevMap getMntrtList(@RequestBody DevMap param) {
        DevMap rslt = new DevMap();
        List<DevMap> resultList = null;

        resultList = carrService.mntrtList(param);
        rslt.put("mntrtList", resultList);
        return rslt;
    }

    /**
     * 요금제 사용
     * @param param
     * @return
     */
    @PostMapping("/carr/insertUseMnt")
    public DevMap insertUseMnt(@RequestBody List<DevMap> param) {
        DevMap rslt = new DevMap();
        carrService.insertUseMnt(param);

        // 정상적으로 insert
        rslt.put("succ", "Y");

        return rslt;
    }

    /**
     * 요금제 미사용처리
     * @param param
     * @return
     */
    @PostMapping("/carr/deleteUseMnt")
    public DevMap deleteUseMnt(@RequestBody List<DevMap> param) {
        DevMap rslt = new DevMap();
        carrService.deleteUseMnt(param);
        rslt.put("succ", "Y");

        return rslt;
    }

    @PostMapping("/carr/getSellDeviceList")
    public DevMap getSellDeviceList(@RequestBody DevMap param) {
        DevMap rslt = new DevMap();
        List<DevMap> resultList = null;

        resultList = carrService.getSellDeviceList(param);
        rslt.put("sellDeviceList", resultList);
        return rslt;
    }

}
