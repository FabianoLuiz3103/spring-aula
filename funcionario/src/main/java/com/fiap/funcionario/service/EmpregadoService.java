package com.fiap.funcionario.service;

import com.fiap.funcionario.dto.EmpregadoDTO;
import com.fiap.funcionario.models.Departamento;
import com.fiap.funcionario.models.Empregado;
import com.fiap.funcionario.models.Projeto;
import com.fiap.funcionario.repository.EmpregadoRepository;
import com.fiap.funcionario.repository.ProjetoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmpregadoService {

    @Autowired
    private ProjetoRepository projetoRepository;

    @Autowired
    private EmpregadoRepository empregadoRepository;

    //Insert
    @Transactional
    public EmpregadoDTO insert(EmpregadoDTO empregadoDTO){
        Empregado entity = new Empregado();
        dtoToEntity(empregadoDTO, entity);
        entity = empregadoRepository.save(entity);
        return new EmpregadoDTO(entity);
    }

    @Transactional(readOnly = true)
    public List<EmpregadoDTO> findAll(){
        return empregadoRepository.findAll().stream().map(EmpregadoDTO::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public EmpregadoDTO findById(Long id){
        Empregado entity = empregadoRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Recurso não encontrado com id: " + id)
        );
        return new EmpregadoDTO(entity);
    }

    @Transactional(readOnly = true)
    public List<EmpregadoDTO> findByDepartamento(Long idDepart) {
        return empregadoRepository.findAll()
                .stream()
                .filter(e -> e.getDepartamento().equals(idDepart))
                .map(EmpregadoDTO::new)
                .collect(Collectors.toList());
    }


    @Transactional
    public EmpregadoDTO update(Long id, EmpregadoDTO empregadoDTO){
        try{
            Empregado entity = new Empregado();
            dtoToEntity(empregadoDTO, entity);
            entity = empregadoRepository.save(entity);
            return new EmpregadoDTO(entity);
        }catch (EntityNotFoundException e){
            throw new IllegalArgumentException("Recurso não encontrado com id: " + id);
        }
    }

    @Transactional
    public void delete(Long id){
        if(!empregadoRepository.existsById(id)){
            throw new IllegalArgumentException("Recurso não encontrado com id: " + id);
        }
        try{
            empregadoRepository.deleteById(id);
        }catch (EntityNotFoundException e){
            throw new IllegalArgumentException("Recurso não encontrado com id: " + id);
        }
    }

    private void dtoToEntity(EmpregadoDTO dto, Empregado entity){
        entity.setNome(dto.getNome());
        entity.setEmail(dto.getEmail());
        entity.setSalario(dto.getSalario());
        entity.setDepartamento(dto.getDepartamento());
        entity.getProjetos().clear();
        for(Projeto p: dto.getProjetos()){
            Projeto projeto = projetoRepository.getReferenceById(p.getId());
            entity.getProjetos().add(projeto);
        }
    }
}
