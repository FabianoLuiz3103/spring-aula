package br.com.fiap.produtomvc.controllers;

import br.com.fiap.produtomvc.dto.CategoriaDTO;
import br.com.fiap.produtomvc.dto.LojaDTO;
import br.com.fiap.produtomvc.dto.ProdutoDTO;
import br.com.fiap.produtomvc.models.Categoria;
import br.com.fiap.produtomvc.models.Loja;
import br.com.fiap.produtomvc.models.Produto;
import br.com.fiap.produtomvc.services.CategoriaService;
import br.com.fiap.produtomvc.services.LojaService;
import br.com.fiap.produtomvc.services.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

// -- código omitido
//URL - localhost:8080/produtos
@Controller
@RequestMapping("/produtos")
public class ProdutoController {
    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private LojaService lojaService;

    @ModelAttribute("lojas")
    public List<LojaDTO> lojas(){
        return lojaService.findAll();
    }

    /*
    Mandando as categorias pelo model para todas as views possíveis
     */
    @ModelAttribute("categorias")
    public List<CategoriaDTO> categorias(){

        return categoriaService.findAll();
    }



    //URL - localhost:8080/produtos/form
    @GetMapping("/form")
    public String loadForm(Model model) {
        model.addAttribute("produtoDTO", new ProdutoDTO());
        //model.addAttribute("categorias", categoriaRepository.findAll()); usando isso retorna só para novo produto
        return "produto/novo-produto";
    }

    @PostMapping()
    public String insert(@Valid ProdutoDTO produtoDto,
                                BindingResult result,
                                RedirectAttributes attributes) {
        if(result.hasErrors()){
            return "produto/novo-produto";
        }
        produtoService.insert(produtoDto);
        attributes.addFlashAttribute("mensagem", "Produto salvo com sucesso");
        return "redirect:/produtos/form";
    }

    //URL - localhost:8080/produtos/listar
    @GetMapping()
    public String findAll(Model model){
        model.addAttribute("produtosDTO", produtoService.findAll());
        return "/produto/listar-produtos"; //View
    }

    //URL - localhost:8080/produtos/produtos/1
    @GetMapping("/{id}")
    public String findById(@PathVariable ("id") Long id, Model model ){
        model.addAttribute("produtoDTO", produtoService.findById(id));
        return "/produto/editar-produto";
    }

    @GetMapping("/prods/{id}")
    public String findByLoja(@PathVariable("id") Long id,
                             Model model){
        model.addAttribute("produtosDTO", produtoService.findByLoja(id));
        model.addAttribute("nomeLoja", lojaService.findById(id).getNome());
        return "produto/listar-produtos-loja";
    }

    //URL - localhost:8080/produtos/editar/1
    @PutMapping("/{id}")
    public String update(@PathVariable("id") Long id, @Valid ProdutoDTO produtoDto,
                                BindingResult result){
        if(result.hasErrors()){
            produtoDto.setId(id);
            return "/produto/editar-produto";
        }
        produtoService.update(id, produtoDto);
        return "redirect:/produtos";
    }

    //URL - localhost:8080/produtos/deletar/1
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id, Model model){
        produtoService.delete(id);
        return "redirect:/produtos";
    }
}










