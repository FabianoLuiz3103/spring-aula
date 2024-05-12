package com.fiap.funcionario.controllers;

import com.fiap.funcionario.dto.DepartamentoDTO;
import com.fiap.funcionario.dto.EmpregadoDTO;
import com.fiap.funcionario.dto.ProjetoDTO;
import com.fiap.funcionario.service.DepartamentoService;
import com.fiap.funcionario.service.EmpregadoService;
import com.fiap.funcionario.service.ProjetoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/empregados")
public class EmpregadoController {

    @Autowired
    private EmpregadoService empregadoService;

    @Autowired
    private DepartamentoService departamentoService;

    @Autowired
    private ProjetoService projetoService;

    @ModelAttribute("departamentosDTO")
    public List<DepartamentoDTO> departamentos(){
        return departamentoService.findAll();
    }

    @ModelAttribute("projetosDTO")
    public List<ProjetoDTO> projetos(){
        return projetoService.findAll();
    }

    @GetMapping("/form")
    public String loadForm(Model model){
        model.addAttribute("empregadoDTO", new EmpregadoDTO());
        return "empregado/novo-empregado";
    }

    @PostMapping
    public String insert(@Valid EmpregadoDTO empregadoDTO,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes){
        if(bindingResult.hasErrors()){
            return "empregado/novo-empregado";
        }
        empregadoDTO = empregadoService.insert(empregadoDTO);
        redirectAttributes.addFlashAttribute("mensagem", "Empregado cadastrado com sucesso!");
        return "redirect:/empregados/form";
    }

    @GetMapping
    public String findAll(Model model){
        model.addAttribute("empregadosDTO", empregadoService.findAll());
        return "empregado/listar-empregados";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable("id") Long id, Model model){
        model.addAttribute("empregadoDTO", empregadoService.findById(id));
        return "empregado/editar-empregado";
    }

    @GetMapping("/empts/{id}")
    public String findByDept(@PathVariable("id") Long id, Model model){
        model.addAttribute("empregadosDTO", empregadoService.findByDepartamento(id));
        model.addAttribute("nomeDPT", departamentoService.findById(id).getNome());
        return "empregado/listar-empregados-departamento";
    }

    @PutMapping("/{id}")
    public String update(@PathVariable("id") Long id,
                         @Valid EmpregadoDTO empregadoDTO,
                         BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            empregadoDTO.setId(id);
            return "empregado/editar-empregado";
        }
        empregadoDTO = empregadoService.update(id, empregadoDTO);
        return "redirect:/empregados";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id){
        empregadoService.delete(id);
        return "redirect:/empregados";
    }

}
