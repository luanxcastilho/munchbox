package br.com.fiap.munchbox.helpers;

import br.com.fiap.munchbox.entities.UsuarioPerfil;

import java.time.LocalDateTime;

public abstract class UsuarioPerfilHelper
{
    public static UsuarioPerfil gerarUsuarioPerfil()
    {
        return UsuarioPerfil
                .builder()
                .idUsuarioPerfil(12345L)
                .nome("Mockito")
                .dataAtualizacao(LocalDateTime.now())
                .dataInclusao(LocalDateTime.now())
                .build();
    }


}
