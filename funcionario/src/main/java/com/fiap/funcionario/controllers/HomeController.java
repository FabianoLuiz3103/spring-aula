package com.fiap.funcionario.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@RequestMapping("/")
public class HomeController {

    @GetMapping()
    public String index(Model model){
        model.addAllAttributes(Map.of(
                "msg", "Bem vindo(a) ao NexTech Solutions"
        ));
        return "/home/index";
    }
}
