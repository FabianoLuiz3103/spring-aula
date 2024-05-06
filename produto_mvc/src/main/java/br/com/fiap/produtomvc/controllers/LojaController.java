package br.com.fiap.produtomvc.controllers;

import br.com.fiap.produtomvc.dto.LojaDTO;
import br.com.fiap.produtomvc.services.LojaService;
import br.com.fiap.produtomvc.services.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/lojas")
public class LojaController {

    @Autowired
    private LojaService lojaService;

    @Autowired
    private ProdutoService produtoService;

    @GetMapping("/form")
    public String loadForm(Model model){
        model.addAttribute("lojaDTO", new LojaDTO());
        return "loja/nova-loja";
    }

    @PostMapping
    public String save(@Valid LojaDTO dto,
                       BindingResult result,
                       RedirectAttributes redirect){
        if(result.hasErrors()){
            return "loja/nova-loja";
        }
        dto = lojaService.insert(dto);
        redirect.addFlashAttribute("mensagem", "Loja cadastrada com sucesso!");
        return "/lojas/form";
    }

    @GetMapping
    public String findAll(Model model){
        model.addAttribute("lojasDTO", lojaService.findAll());
        return "loja/listar-lojas";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable("id") Long id,
                           Model model){
        model.addAttribute("lojaDTO", lojaService.findById(id));
        return "loja/editar-loja";
    }

    @GetMapping("/prods/{id}")
    public String findByLoja(@PathVariable("id") Long id,
                           Model model){
        model.addAttribute("produtosDTO", produtoService.findByLoja(id));
        model.addAttribute("nomeLoja", lojaService.findById(id).getNome());
        return "produto/listar-produtos-loja";
    }

    @PutMapping("/{id}")
    public String update(@PathVariable("id") Long id,
                         @Valid LojaDTO dto,
                         BindingResult result){
        if(result.hasErrors()){
            dto.setId(id);
            return "loja/editar-loja";
        }
        dto = lojaService.update(id, dto);
        return "/lojas";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id){
        lojaService.delete(id);
        return "/lojas";
    }


}
