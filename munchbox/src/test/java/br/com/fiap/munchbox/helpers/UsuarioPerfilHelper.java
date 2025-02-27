package br.com.fiap.munchbox.helpers;

import br.com.fiap.munchbox.entities.UsuarioPerfil;

import java.time.LocalDateTime;

public abstract class UsuarioPerfilHelper
{

    public static UsuarioPerfil gerarUsuarioPerfil()
    {
        return UsuarioPerfil.builder().idUsuarioPerfil(1L).nome("Mockito").dataAtualizacao(LocalDateTime.of(1993, 2, 21, 0, 0)).dataInclusao(LocalDateTime.of(1993, 2, 21, 0, 0)).build();
    }
}
