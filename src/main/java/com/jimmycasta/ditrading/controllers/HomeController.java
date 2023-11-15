package com.jimmycasta.ditrading.controllers;

import com.jimmycasta.ditrading.utils.operationMath;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("statisticsDTO",operationMath.getAllStatistics());
        return "home";
    }
}
