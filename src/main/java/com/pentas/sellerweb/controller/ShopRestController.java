package com.pentas.sellerweb.controller;

import com.pentas.sellerweb.common.module.util.DevMap;
import com.pentas.sellerweb.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShopRestController {

    @Autowired
    ShopService shopService;

    @PostMapping("/shop/getShopInfo")
    public DevMap getShopInfo(@RequestBody DevMap param) {
        DevMap rslt = new DevMap();
        rslt = shopService.shopInfo(param);
        return rslt;
    }

}
