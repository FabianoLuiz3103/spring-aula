package com.fiap.funcionario.repository;

import com.fiap.funcionario.models.Departamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartamentoRepository extends JpaRepository<Departamento, Long> {
}
