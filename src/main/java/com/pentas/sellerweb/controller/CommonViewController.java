package com.pentas.sellerweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CommonViewController {

    @GetMapping("/")
    public String index() {
        return "page/main";
    }

    @GetMapping("/join")
    public String join() { return "page/join-agree"; }

    @GetMapping("/join/form")
    public String joinForm() { return "page/join-form"; }

}