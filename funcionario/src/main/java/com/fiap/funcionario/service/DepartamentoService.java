package com.fiap.funcionario.service;

import com.fiap.funcionario.dto.DepartamentoDTO;
import com.fiap.funcionario.models.Departamento;
import com.fiap.funcionario.repository.DepartamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartamentoService {

    @Autowired
    private DepartamentoRepository departamentoRepository;

    @Transactional
    public DepartamentoDTO insert(DepartamentoDTO departamentoDTO){
        Departamento entity = new Departamento();
        dtoToEntity(departamentoDTO, entity);
        entity = departamentoRepository.save(entity);
        return new DepartamentoDTO(entity);
    }

    @Transactional(readOnly = true)
    public List<DepartamentoDTO> findAll(){
        return departamentoRepository.findAll().stream().map(DepartamentoDTO::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public DepartamentoDTO findById(Long id){
        Departamento entity = departamentoRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Recurso não encontrado com id: " + id)
        );
        return new DepartamentoDTO(entity);
    }

    private void dtoToEntity(DepartamentoDTO dto, Departamento entity){
        entity.setNome(dto.getNome());
    }
}
