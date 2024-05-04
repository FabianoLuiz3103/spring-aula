package br.com.fiap.produtomvc.services;

import br.com.fiap.produtomvc.dto.CategoriaDTO;
import br.com.fiap.produtomvc.models.Categoria;
import br.com.fiap.produtomvc.repository.CategoriaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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
    public CategoriaDTO insert(CategoriaDTO dto){
        Categoria entity = new Categoria();
        dtoToEntity(dto, entity);
        repository.save(entity);
        return new CategoriaDTO(entity);
    }

    /*
    Método que retorna uma lista de categorias
     */
    @Transactional(readOnly = true)
    public List<CategoriaDTO> findAll(){
        List<Categoria> list = repository.findAll();
        return list.stream().map(CategoriaDTO::new).collect(Collectors.toList());
    }

    /*
    Método que recupera por id
     */
    @Transactional(readOnly = true)
    public CategoriaDTO findById(Long id){
        Categoria categoria = repository.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("Categoria não encontrada com id: " + id)
        );
        return new CategoriaDTO(categoria);
    }

    @Transactional
    public CategoriaDTO update(Long id, CategoriaDTO dto){
        try{
            Categoria categoria = repository.getReferenceById(id);
            dtoToEntity(dto, categoria);
            categoria = repository.save(categoria);
            return new CategoriaDTO(categoria);
        }catch (EntityNotFoundException ex){
            throw new IllegalArgumentException("Categoria não encontrada!");
        }
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

    private void dtoToEntity(CategoriaDTO dto, Categoria entity){
        entity.setNome(dto.getNome());
    }
}
