package br.com.fiap.munchbox.interfaces.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioRequestDTO
{
    private String login;
    private String senha;
    private Long idUsuarioPerfil;
}
