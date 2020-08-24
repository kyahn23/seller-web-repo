package com.pentas.sellerweb.controller;

import com.pentas.sellerweb.common.module.util.DevMap;
import com.pentas.sellerweb.service.CarrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CarrRestController {

    @Autowired
    CarrService carrService;

    @PostMapping("/carr/getMntrtList")
    public DevMap getMntrtList(@RequestBody DevMap param) {
        DevMap rslt = new DevMap();
        List<DevMap> resultList = null;

        resultList = carrService.mntrtList(param);
        rslt.put("mntrtList", resultList);
        return rslt;
    }

    @PostMapping("/carr/insertUseMnt")
    public DevMap insertUseMnt(@RequestBody DevMap param){
        DevMap rslt = new DevMap();
        carrService.insertUseMnt(param);
        // 정상적으로 insert
        rslt.put("succ", "Y");

        return rslt;
    }

}
