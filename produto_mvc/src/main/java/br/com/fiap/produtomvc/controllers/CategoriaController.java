package br.com.fiap.produtomvc.controllers;

import br.com.fiap.produtomvc.dto.CategoriaDTO;
import br.com.fiap.produtomvc.models.Categoria;
import br.com.fiap.produtomvc.models.Produto;
import br.com.fiap.produtomvc.repository.CategoriaRepository;
import br.com.fiap.produtomvc.services.CategoriaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService service;

    @GetMapping("/form")
    public String loadFormCategoria(Model model){
        model.addAttribute("categoriaDTO", new CategoriaDTO());
        return "categoria/nova-categoria";
    }

    @PostMapping
    public String insert(@Valid CategoriaDTO categoria,
                         BindingResult result,
                         RedirectAttributes atributes){
        if(result.hasErrors()){
            return "categoria/nova-categoria";
        }
        /*
        Fazer assim para se caso for necessário recuperar o id da categoria inserida.
         */
        categoria = service.insert(categoria);
        atributes.addFlashAttribute("mensagem", "Categoria salva com sucesso!");
        return "redirect:/categorias/form";
    }

    //Carregar categorias
    @GetMapping
    public String findAll(Model model){
        List<CategoriaDTO> categorias = service.findAll();
        model.addAttribute("categoriasDTO", categorias);
        return "/categoria/listar-categorias";
    }

    //Carregar categoria para edição
    @GetMapping("/{id}")
    public String findById(@PathVariable("id") Long id, Model model){
        CategoriaDTO categoria = service.findById(id);
        model.addAttribute("categoriaDTO", categoria);
        return "/categoria/editar-categoria";
    }

    //Receber edição da categoria
    @PutMapping("/{id}")
    public String upadte(@PathVariable("id") Long id,
                         @Valid CategoriaDTO categoria,
                         BindingResult result){

        if(result.hasErrors()){
            categoria.setId(id);
            return "/categoria/editar-categoria";
        }
        service.update(id, categoria);
        return "redirect:/categorias";
    }

    //Excluir categoria
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id")Long id, Model model){
        service.delete(id);
        return "redirect:/categorias";
    }


}
