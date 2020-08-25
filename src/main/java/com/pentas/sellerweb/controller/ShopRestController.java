package com.pentas.sellerweb.controller;

import com.pentas.sellerweb.common.module.util.DevMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShopRestController {

    @PostMapping("/shop/getShopInfo")
    public DevMap getShopInfo() {
        DevMap rslt = new DevMap();
        rslt.put("bnNo", "12345678901234567890");
        rslt.put("bnRegNo", "1234567890");
        rslt.put("bnNm","가나다라마바사아자차카파타하가나다라마바사아자차카파타하가나다라마바사아자차카파타하가나다라마바사아");
        rslt.put("ownerNm","가나다라마바사아자차카파타하가나다라마바");
        rslt.put("bnRegCard","가나다라마바사아자차카파타하가나다라마바사아자차카파타하가나다라마바사아자차카파타하가나다라마바사아");
        rslt.put("bnAddrSido","가나다라마바사아자차카파");
        rslt.put("bnAddrSigg","가나다라마바사아자차카파");
        rslt.put("bnAddrDtl", "가나다라마바사아자차카파타하가나다라마바사아자차카파타하가나다라마바사아자차카파타하가나다라마바사아자차카파타하가나다라마바사아자차카파타하가나다라마바사아자차");
        rslt.put("mstMbrId", "가나다라마바사아자차카파타하가나다라마바사아자차카파타하가나다라마바사아자차카파타하가나다라마바사아");
        rslt.put("cardChkYn", "Y");
        rslt.put("bnPnNo", "01234567890");
        rslt.put("bnImg", "shop.png");

        return rslt;
    }

}
