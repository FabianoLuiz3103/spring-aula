package br.com.fiap.produtomvc.dto;

import br.com.fiap.produtomvc.models.Loja;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LojaDTO {

    private Long id;

    @NotBlank(message = "Campo requerido! ")
    @Size(min = 3, message = "O nome da loja deve ter no mínimo 3 caracteres")
    private String nome;

    public LojaDTO(Loja entity){
        id = entity.getId();
        nome = entity.getNome();
    }
}
