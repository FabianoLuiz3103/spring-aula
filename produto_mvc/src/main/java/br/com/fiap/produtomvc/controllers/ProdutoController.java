package br.com.fiap.produtomvc.controllers;

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
    public List<Loja> lojas(){
        return lojaService.findAll();
    }

    /*
    Mandando as categorias pelo model para todas as views possíveis
     */
    @ModelAttribute("categorias")
    public List<Categoria> categorias(){

        return categoriaService.findAll();
    }



    //URL - localhost:8080/produtos/form
    @GetMapping("/form")
    public String loadForm(Model model) {
        model.addAttribute("produto", new Produto());
        //model.addAttribute("categorias", categoriaRepository.findAll()); usando isso retorna só para novo produto
        return "produto/novo-produto";
    }

    @PostMapping()
    public String insert(@Valid Produto produto,
                                BindingResult result,
                                RedirectAttributes attributes) {
        if(result.hasErrors()){
            return "produto/novo-produto";
        }
        produtoService.insert(produto);
        attributes.addFlashAttribute("mensagem", "Produto salvo com sucesso");
        return "redirect:/produtos/form";
    }

    //URL - localhost:8080/produtos/listar
    @GetMapping()
    public String findAll(Model model){
        model.addAttribute("produtos", produtoService.findAll());
        return "/produto/listar-produtos"; //View
    }

    //URL - localhost:8080/produtos/produtos/1
    @GetMapping("/{id}")
    public String findById(@PathVariable ("id") Long id, Model model ){
        model.addAttribute("produto", produtoService.findById(id));
        return "/produto/editar-produto";
    }

    //URL - localhost:8080/produtos/editar/1
    @PutMapping("/{id}")
    public String update(@PathVariable("id") Long id, @Valid Produto produto,
                                BindingResult result){
        if(result.hasErrors()){
            produto.setId(id);
            return "/produto/editar-produto";
        }
        produtoService.update(id, produto);
        return "redirect:/produtos";
    }

    //URL - localhost:8080/produtos/deletar/1
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id, Model model){
        produtoService.delete(id);
        return "redirect:/produtos";
    }
}










