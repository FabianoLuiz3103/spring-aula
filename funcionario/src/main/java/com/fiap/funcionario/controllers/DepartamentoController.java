package com.fiap.funcionario.controllers;

import com.fiap.funcionario.dto.DepartamentoDTO;
import com.fiap.funcionario.service.DepartamentoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/departamentos")
public class DepartamentoController {

    @Autowired
    private DepartamentoService departamentoService;

    @GetMapping("/form")
    public String loadForm(Model model){
        model.addAttribute("departamentoDTO", new DepartamentoDTO());
        return "departamento/novo-departamento";
    }

    @PostMapping
    public String insert(@Valid DepartamentoDTO departamentoDTO,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes){
        if(bindingResult.hasErrors()){
            return "departamento/novo-departamento";
        }
        departamentoDTO = departamentoService.insert(departamentoDTO);
        redirectAttributes.addFlashAttribute("mensagem", "Departamento cadastrado com sucesso! ");
        return "redirect:/departamentos/form";
    }

    @GetMapping
    public String findAll(Model model){
        model.addAttribute("departamentosDTO", departamentoService.findAll());
        return "departamento/listar-departamentos";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable("id") Long id, Model model){
        model.addAttribute("departamentoDTO", departamentoService.findById(id));
        return "departamento/editar-departamento";
    }

    @PutMapping("/{id}")
    public String update(@PathVariable("id") Long id,
                         @Valid DepartamentoDTO departamentoDTO,
                         BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            departamentoDTO.setId(id);
            return "departamento/editar-departamento";
        }
        departamentoDTO = departamentoService.update(id, departamentoDTO);
        return "redirect:/departamentos";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id){
        departamentoService.delete(id);
        return "redirect:/departamentos";
    }
}
