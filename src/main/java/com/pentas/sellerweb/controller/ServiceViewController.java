package com.pentas.sellerweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ServiceViewController {

    @GetMapping("/service/inbox")
    public String serviceInbox() {
        return "page/service-inbox";
    }

    @GetMapping("/service/visit")
    public String serviceVisit() { return "page/service-visit"; }

    @GetMapping("/service/status")
    public String serviceStatus() { return "page/service-status"; }

    @GetMapping("/service/marketing")
    public String serviceMarketing() { return "page/service-marketing"; }

}
