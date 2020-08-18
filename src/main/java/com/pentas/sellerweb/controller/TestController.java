package com.pentas.sellerweb.controller;

import com.pentas.sellerweb.common.module.util.DevMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @PostMapping("/testpath")
    public DevMap tttt(){
        DevMap rslt = new DevMap();
        rslt.put("id","qwer");
        rslt.put("pass","1234");
        rslt.put("age","33");

        return rslt;
    }
}
