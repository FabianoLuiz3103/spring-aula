package br.com.fiap.produtomvc.controllers;

import br.com.fiap.produtomvc.models.User;
import br.com.fiap.produtomvc.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.BindParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping("/form")
    public String loadForm(Model model){
        model.addAttribute("user", new User());
        return "user/novo-user";
    }

    @PostMapping
    public String insert(@Valid User user,
                         BindingResult result,
                         RedirectAttributes atributes){
        return null;
    }

}
