package br.com.fiap.munchbox.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UsuarioPerfilRequestDTO
{
    @NotBlank(message = "O nome é obrigatório")
    private String nome;
}
