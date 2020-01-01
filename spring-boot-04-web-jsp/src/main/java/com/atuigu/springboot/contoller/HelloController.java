package com.atuigu.springboot.contoller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("/success")
    public String success(Model model){
        model.addAttribute("age", 18);
        return "success";
    }
}
