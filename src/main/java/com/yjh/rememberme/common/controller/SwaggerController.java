package com.yjh.rememberme.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/swagger")
public class SwaggerController {
    @RequestMapping("")
    public String swaggerRoot(){
        return "redirect:/swagger-ui.html";
    }
}
