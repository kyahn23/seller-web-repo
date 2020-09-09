package com.pentas.sellerweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CounselViewController {

    @GetMapping("/service/inbox")
    public String counselInbox() {
        return "page/service-inbox";
    }

    @GetMapping("/service/visit")
    public String counselVisit() { return "page/service-visit"; }

    @GetMapping("/service/status")
    public String counselStatus() { return "page/service-status"; }

    @GetMapping("/service/marketing")
    public String counselMarketing() { return "page/service-marketing"; }

}
