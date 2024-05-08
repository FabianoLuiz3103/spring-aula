package com.fiap.funcionario.dto;

import com.fiap.funcionario.models.Departamento;
import com.fiap.funcionario.models.Empregado;
import com.fiap.funcionario.models.Projeto;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EmpregadoDTO {

    private Long id;
    @NotBlank(message = "Campo requerido! ")
    @Size(min=3, message = "O nome deve ter no mínimo 3 caracteres!")
    private String nome;
    @NotBlank(message = "Campo requerido! ")
    @Email(message = "O formato do email está inválido! ")
    private String email;
    @NotNull(message = "Campo requerido! ")
    @Positive(message = "O salário deve ser positivo! ")
    private double salario;
    private Departamento departamento;
    private Set<Projeto> projetos = new HashSet<>();

    public EmpregadoDTO(Empregado entity){
        id = entity.getId();
        nome = entity.getNome();
        email = entity.getEmail();
        salario = entity.getSalario();
        departamento = entity.getDepartamento();
        projetos.addAll(entity.getProjetos());
    }
}
