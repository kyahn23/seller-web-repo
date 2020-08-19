package com.pentas.sellerweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ShopViewController {

    @GetMapping("/shop/manage")
    public String shopManage() { return "page/shop-manage"; }

    @GetMapping("/shop/employees")
    public String shopEmployees() { return "page/shop-employees"; }

    @GetMapping("/shop/notice")
    public String shopNotice() { return "page/shop-notice"; }

}
