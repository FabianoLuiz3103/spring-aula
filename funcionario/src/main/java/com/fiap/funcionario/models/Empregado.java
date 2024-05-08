package com.fiap.funcionario.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.EqualsAndHashCode;

import java.util.HashSet;
import java.util.Set;

//Anotações Lombok
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@EqualsAndHashCode(of = {"id"})
//Anotações JPA
@Entity
@Table(name = "tbl_empregados")
public class Empregado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="departamento_id", nullable = false)
    private Departamento departamento;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "tb_empregado_projeto",
    joinColumns = @JoinColumn(name="empregado_id"),
    inverseJoinColumns = @JoinColumn(name="projeto_id"))
    private Set<Projeto> projetos = new HashSet<>();

}
