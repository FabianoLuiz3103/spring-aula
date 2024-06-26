package br.com.fiap.produtomvc.dto;

import br.com.fiap.produtomvc.models.Categoria;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class CategoriaDTO {

    private Long id;

    @NotBlank(message = "Campo requerido! ")
    @Size(min=3, message = "O nome deve ter no mínimo 3 caracteres! ")
    private String nome;

    public CategoriaDTO(Categoria entity){
        id = entity.getId();
        nome = entity.getNome();
    }
}
