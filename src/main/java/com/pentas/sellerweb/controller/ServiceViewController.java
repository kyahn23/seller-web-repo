package com.pentas.sellerweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ServiceViewController {

    @GetMapping("/service/inbox")
    public String serviceInbox() {
        return "service-inbox";
    }

    @GetMapping("/page/service/inbox")
    public String pageServiceInbox() {
        return "page/service-inbox";
    }

}
