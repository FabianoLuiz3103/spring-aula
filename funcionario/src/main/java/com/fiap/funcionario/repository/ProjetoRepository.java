package com.fiap.funcionario.repository;

import com.fiap.funcionario.models.Projeto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjetoRepository extends JpaRepository<Projeto, Long> {
}
