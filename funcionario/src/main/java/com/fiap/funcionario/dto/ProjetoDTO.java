package com.fiap.funcionario.dto;

import com.fiap.funcionario.models.Projeto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class ProjetoDTO {

    private Long id;
    @NotBlank(message = "Campo requerido")
    @Size(min=3, message = "O nome deve ter no mínimo 3 caracteres! ")
    private String nome;

    public ProjetoDTO(Projeto entity){
        id = entity.getId();
        nome = entity.getNome();
    }
}
