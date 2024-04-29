package br.com.fiap.produtomvc.services;

import br.com.fiap.produtomvc.models.Categoria;
import br.com.fiap.produtomvc.repository.CategoriaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoriaService {

    /*
    Injeção de depêndencias
     */
    @Autowired
    private CategoriaRepository repository;

    /*
    Método que cadastra uma nova categoria
     */
    @Transactional
    public Categoria insert(Categoria categoria){
        return repository.save(categoria);
    }

    /*
    Método que retorna uma lista de categorias
     */
    @Transactional(readOnly = true)
    public List<Categoria> findAll(){
        return repository.findAll();
    }

    /*
    Método que recupera por id
     */
    @Transactional(readOnly = true)
    public Categoria findById(Long id){
        Categoria categoria = repository.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("Categoria não encontrada com id: " + id)
        );
        return categoria;
    }

    @Transactional
    public Categoria update(Long id, Categoria entity){
        try{
            Categoria categoria = repository.getReferenceById(id);
            copyToCategoria(entity, categoria);
            categoria = repository.save(categoria);
            return categoria;
        }catch (EntityNotFoundException ex){
            throw new IllegalArgumentException("Categoria não encontrada!");
        }
    }

    /*
    Método privado que faz a alteração do nome da categoria, recebendo o nome antigo e o novo.
     */
    private void copyToCategoria(Categoria entity, Categoria categoria){
        categoria.setNome(entity.getNome());
    }

    @Transactional
    public void delete(Long id){
        if (!repository.existsById(id)){
            throw new IllegalArgumentException("Categoria não encontrada com id: " + id);
        }
        try{
           repository.deleteById(id);
        }catch (DataIntegrityViolationException e){
            throw new IllegalArgumentException("Falha de integridade referencial para o id: " + id);
        }
    }
}
