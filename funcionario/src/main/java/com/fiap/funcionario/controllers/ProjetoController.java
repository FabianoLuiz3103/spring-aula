package com.fiap.funcionario.controllers;

import com.fiap.funcionario.service.EmpregadoService;
import com.fiap.funcionario.service.ProjetoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/projetos")
public class ProjetoController {

    @Autowired
    private ProjetoService projetoService;

    @Autowired
    EmpregadoService empregadoService;

    @GetMapping("/pjts/{id}")
    public String findByEmpt(@PathVariable("id") Long id, Model model){
        model.addAttribute("projetosDTO", projetoService.findByEmpt(id));
        model.addAttribute("nomeEMPT", empregadoService.findById(id).getNome());
        return "projeto/listar-projetos-empregado";
    }
}
