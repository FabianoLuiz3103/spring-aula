package br.com.fiap.produtomvc.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@EqualsAndHashCode(exclude = {"nome", "email", "dataNascimento"})
@Entity
@Table(name="tbl_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Campo requerido!")
    @Size(min = 3, message = "O nome deve ter no mínimo 3 caracteres! ")
    private String nome;

    @NotBlank(message = "Campo requerido!")
    @Email(message = "O formato do email é inváldo!")
    private String email;

    @NotBlank(message = "Campo requerido! ")
    private String dataNascimento;

}
