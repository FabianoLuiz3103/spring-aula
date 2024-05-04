package br.com.fiap.produtomvc.services;

import br.com.fiap.produtomvc.dto.LojaDTO;
import br.com.fiap.produtomvc.models.Loja;
import br.com.fiap.produtomvc.models.Produto;
import br.com.fiap.produtomvc.repository.LojaRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LojaService {

    @Autowired
    private LojaRepository repository;

    @Transactional
    public LojaDTO insert(LojaDTO dto){
        Loja entity = new Loja();
        entity.setNome(dto.getNome());
        repository.save(entity);
        return new LojaDTO(entity);
    }

    @Transactional(readOnly = true)
    public List<LojaDTO> findAll(){
        List<Loja> lista = repository.findAll();
        return lista.stream().map(LojaDTO::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public LojaDTO findById(Long id){
        Loja loja = repository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Recurso não encontrado para o id: " + id)
        );
        return new LojaDTO(loja);
    }

    @Transactional
    public LojaDTO update(Long id, LojaDTO dto){
        try{
            Loja loja = repository.getReferenceById(id);
            loja.setNome(dto.getNome());
            repository.save(loja);
            return new LojaDTO(loja);
        }catch(EntityNotFoundException e){
            throw new IllegalArgumentException("Recurso não encontrado para o id: " + id);
        }
    }

    @Transactional
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new IllegalArgumentException("Recurso não encontrado para o id: " + id);
        }
        try {
            repository.deleteById(id);
        } catch (Exception e) {
            throw new IllegalArgumentException("Recurso não encontrado para o id: " + id);
        }
    }

}
