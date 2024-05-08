package com.fiap.funcionario.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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
@Table(name = "tbl_projetos")
public class Projeto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Campo requerido")
    @Size(min=3, message = "O nome deve ter no mínimo 3 caracteres! ")
    private String nome;

    @ManyToMany(mappedBy = "projetos", fetch = FetchType.EAGER)
    private Set<Projeto> empregados = new HashSet<>();
}
