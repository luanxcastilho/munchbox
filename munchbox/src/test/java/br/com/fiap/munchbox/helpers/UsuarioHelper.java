package br.com.fiap.munchbox.helpers;

import br.com.fiap.munchbox.entities.Usuario;
import br.com.fiap.munchbox.entities.UsuarioPerfil;

import java.time.LocalDateTime;

public abstract class UsuarioHelper
{
    public static Usuario gerarUsuario()
    {
        return Usuario
                .builder()
                .idUsuario(1L)
                .login("mockito")
                .senha("mockito")
                .dataAtualizacao(LocalDateTime.now())
                .dataInclusao(LocalDateTime.now())
                .build();
    }
}
