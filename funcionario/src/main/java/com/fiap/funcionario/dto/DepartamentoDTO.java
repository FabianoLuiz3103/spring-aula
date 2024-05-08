package com.fiap.funcionario.dto;

import com.fiap.funcionario.models.Departamento;
import com.fiap.funcionario.models.Empregado;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class DepartamentoDTO {

    private Long id;
    @NotBlank(message = "Campo requerido")
    @Size(min=3, message = "O nome deve ter no mínimo 3 caracteres! ")
    private String nome;

    public DepartamentoDTO(Departamento entity){
        id = entity.getId();
        nome = entity.getNome();
    }
}
