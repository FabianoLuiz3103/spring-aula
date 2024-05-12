package com.fiap.funcionario.service;

import com.fiap.funcionario.dto.DepartamentoDTO;
import com.fiap.funcionario.models.Departamento;
import com.fiap.funcionario.repository.DepartamentoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
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

    @Transactional
    public DepartamentoDTO update(Long id, DepartamentoDTO departamentoDTO){
        try{
            Departamento entity = departamentoRepository.getReferenceById(id);
            dtoToEntity(departamentoDTO, entity);
            entity = departamentoRepository.save(entity);
            return new DepartamentoDTO(entity);
        }catch(EntityNotFoundException e){
            throw new IllegalArgumentException("Recurso não encontrado para o id: " + id);
        }
    }

    @Transactional
    public void delete(Long id){
        if(!departamentoRepository.existsById(id)){
            throw new IllegalArgumentException("Recurso não encontrado com id: " + id);
        }
        try{departamentoRepository.deleteById(id);
        }catch (DataIntegrityViolationException e){
            throw new IllegalArgumentException("Falha de integridade referencial para o id: " + id);
        }
    }

    private void dtoToEntity(DepartamentoDTO dto, Departamento entity){
        entity.setNome(dto.getNome());
    }
}
