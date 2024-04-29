package br.com.fiap.produtomvc.services;

import br.com.fiap.produtomvc.models.Loja;
import br.com.fiap.produtomvc.repository.LojaRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Service
public class LojaService {

    @Autowired
    private LojaRepository repository;

    @Transactional(readOnly = true)
    public List<Loja> findAll(){
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public Loja findById(Long id){
        return repository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Recurso não encontrado para o id: " + id)
        );
    }

    @Transactional
    public Loja update(Long id, Loja entity){
        try{
            Loja loja = repository.getReferenceById(id);
            loja.setNome(entity.getNome());
            repository.save(loja);
            return loja;
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
