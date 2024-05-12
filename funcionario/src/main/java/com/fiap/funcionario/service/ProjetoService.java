package com.fiap.funcionario.service;

import com.fiap.funcionario.dto.ProjetoDTO;
import com.fiap.funcionario.models.Projeto;
import com.fiap.funcionario.repository.ProjetoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjetoService {

    @Autowired
    private ProjetoRepository projetoRepository;

    @Transactional(readOnly = true)
    public List<ProjetoDTO> findAll(){
        return projetoRepository.findAll().stream().map(ProjetoDTO::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ProjetoDTO findById(Long id){
        Projeto entity = projetoRepository.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("Recurso não encontrado com id: " + id)
        );
        return new ProjetoDTO(entity);
    }

    @Transactional(readOnly = true)
    public List<ProjetoDTO> findByEmpt(Long idEmpt){
        return projetoRepository.findAll().stream().filter(p -> p.getEmpregados().stream().anyMatch(e -> e.getId().equals(idEmpt)))
                .toList().stream().map(ProjetoDTO::new).collect(Collectors.toList());
    }

}
