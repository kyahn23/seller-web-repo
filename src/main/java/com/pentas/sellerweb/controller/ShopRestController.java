package com.pentas.sellerweb.controller;

import com.pentas.sellerweb.common.module.util.DevMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShopRestController {

    @PostMapping("/shop/getShopInfo")
    public DevMap getShopInfo() {
        DevMap rslt = new DevMap();
        rslt.put("bnNm","가나다라마바사아자차카파타하가나다라마바사아자차카파타하가나다라마바사아자차카파타하가나다라마바사아");
        rslt.put("bnAddrSido","가나다라마바사아자차카파");
        rslt.put("bnAddrSigg","가나다라마바사아자차카파타하");
        rslt.put("bnAddrDtl", "가나다라마바사아자차카파타하가나다라마바사아자차카파타하가나다라마바사아자차카파타하가나다라마바사아자차카파타하가나다라마바사아자차카파타하가나다라마바사아자차");
        rslt.put("bnRegNo", "가나다라마바사아자차카파타하가나다라마바");
        rslt.put("bnImg", "가나다라마바사아자차카파타하가나다라마바사아자차카파타하가나다라마바사아자차카파타하가나다라마바사아");

        return rslt;
    }

}
