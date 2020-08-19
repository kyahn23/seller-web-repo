package com.pentas.sellerweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class CarrViewController {

    @GetMapping("/carr/deal/{carrier}")
    public String carrDeal(@PathVariable String carrier, Model model){
        model.addAttribute("carr", carrier);
        return "page/carr-deal-manage";
    }

    @GetMapping("/carr/mntrt/{carrier}")
    public String carrMntrt(){
        return "page/carr-mntrt-manage";
    }
}
