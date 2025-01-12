package br.com.fiap.munchbox.dtos.usuario;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AlterarSenhaResquestDTO {

    @NotBlank(message = "O usuário é obrigatório")
    private String usuario;

    @NotBlank(message = "A senha é obrigatória")
    private String senha;

    @NotBlank(message = "A nova senha é obrigatória")
    private String novaSenha;

}
