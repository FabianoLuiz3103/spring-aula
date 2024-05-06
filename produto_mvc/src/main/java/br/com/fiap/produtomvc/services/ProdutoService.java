package br.com.fiap.produtomvc.services;

import br.com.fiap.produtomvc.dto.ProdutoDTO;
import br.com.fiap.produtomvc.models.Loja;
import br.com.fiap.produtomvc.models.Produto;
import br.com.fiap.produtomvc.repository.LojaRepository;
import br.com.fiap.produtomvc.repository.ProdutoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private LojaRepository lojaRepository;

    @Transactional
    public ProdutoDTO insert(ProdutoDTO dto){
        Produto produto = new Produto();
        copyDtoToEntity(dto, produto);
        produtoRepository.save(produto);
        return new ProdutoDTO(produto);
    }

    @Transactional(readOnly = true)
    public List<ProdutoDTO> findAll(){
        return produtoRepository.findAll().stream().map(ProdutoDTO::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ProdutoDTO findById(Long id){
        Produto produto = produtoRepository.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("Recurso não encontrado para o id: " + id)
        );
        return new ProdutoDTO(produto);
    }

    @Transactional(readOnly = true)
    public List<ProdutoDTO> findByLoja(Long idLoja) {
        List<Produto> list = produtoRepository.findAll();
        return list.stream()
                .filter(p -> p.getLojas().stream().anyMatch(l -> l.getId().equals(idLoja)))
                .toList().stream().map(ProdutoDTO::new).collect(Collectors.toList());
    }


    /*
    Está recebendo o id e o produto entity com os dados atualizados
     */
    @Transactional
    public ProdutoDTO update(Long id, ProdutoDTO dto){
        try{
            /*
            Pega a referência produto que já existe no banco, ou seja
            não trás os dados diferente do findById
             */
            Produto produto = produtoRepository.getReferenceById(id);
            /*
            Atualiaza o produto recuperado do banco com os dados do produto
            atualizado recebido
             */
            copyDtoToEntity(dto,produto);
            produto = produtoRepository.save(produto);
            return new ProdutoDTO(produto);

        }catch(EntityNotFoundException e){
            throw new IllegalArgumentException("Recurso não encontrado para o id: " + id);
        }

    }

    /*
    Criando uma cópia, estou setando os dados que vieram do formulário no produto com referência
    ao id x
     */
    private void copyDtoToEntity(ProdutoDTO dto, Produto entity){
        entity.setNome(dto.getNome());
        entity.setDescricao(dto.getDescricao());
        entity.setValor(dto.getValor());
        entity.setCategoria(dto.getCategoria());
        entity.getLojas().clear();
        for(Loja item:dto.getLojas()){
            Loja loja = lojaRepository.getReferenceById(item.getId());
            entity.getLojas().add(loja);
        }

    }

    @Transactional
    public void delete(Long id){
        if(!produtoRepository.existsById(id)){
            throw new IllegalArgumentException("Recurso não encontrado para o id: " + id);
        }
        try{
            produtoRepository.deleteById(id);
        }catch(Exception e){
            throw new IllegalArgumentException("Recurso não encontrado para o id: " + id);
        }
    }

}
