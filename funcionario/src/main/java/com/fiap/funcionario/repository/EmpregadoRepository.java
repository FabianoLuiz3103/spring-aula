package com.fiap.funcionario.repository;

import com.fiap.funcionario.models.Empregado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpregadoRepository extends JpaRepository<Empregado, Long> {
}
