package br.com.fiap.produtomvc.services;

import br.com.fiap.produtomvc.models.Loja;
import br.com.fiap.produtomvc.models.Produto;
import br.com.fiap.produtomvc.repository.ProdutoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.sound.sampled.Port;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository repository;

    @Transactional
    public Produto insert(Produto produto){
        return repository.save(produto);
    }

    @Transactional(readOnly = true)
    public List<Produto> findAll(){
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public Produto findById(Long id){
        /*
        Ele vai no banco e busca todos os dados de produto
         */
        Produto produto = repository.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("Recurso não encontrado para o id: " + id)
        );
        return produto;
    }

    @Transactional(readOnly = true)
    public List<Produto> findByLoja(Long idLoja) {
        List<Produto> list = repository.findAll();
        return list.stream()
                .filter(p -> p.getLojas().stream().anyMatch(l -> l.getId().equals(idLoja)))
                .collect(Collectors.toList());
    }


    /*
    Está recebendo o id e o produto entity com os dados atualizados
     */
    @Transactional
    public Produto  update(Long id, Produto entity){
        try{
            /*
            Pega a referência produto que já existe no banco, ou seja
            não trás os dados diferente do findById
             */
            Produto produto = repository.getReferenceById(id);
            /*
            Atualiaza o produto recuperado do banco com os dados do produto
            atualizado recebido
             */
            atualizaProduto(entity, produto);
            produto = repository.save(produto);
            return produto;

        }catch(EntityNotFoundException e){
            throw new IllegalArgumentException("Recurso não encontrado para o id: " + id);
        }

    }

    /*
    Criando uma cópia, estou setando os dados que vieram do formulário no produto com referência
    ao id x
     */
    private void atualizaProduto(Produto entity, Produto produto){
        produto.setNome(entity.getNome());
        produto.setCategoria(entity.getCategoria());
        produto.setDescricao(entity.getDescricao());
        produto.setValor(entity.getValor());

        produto.getLojas().clear();
        for (Loja loja :  entity.getLojas()){
            Loja loja1 = new Loja();
            loja1.setId(loja.getId());
            produto.getLojas().add(loja1);
        }
    }

    @Transactional
    public void delete(Long id){
        if(!repository.existsById(id)){
            throw new IllegalArgumentException("Recurso não encontrado para o id: " + id);
        }
        try{
            repository.deleteById(id);
        }catch(Exception e){
            throw new IllegalArgumentException("Recurso não encontrado para o id: " + id);
        }
    }

}
