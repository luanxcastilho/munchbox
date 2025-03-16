package br.com.fiap.munchbox.infrastructure.mapper;

import br.com.fiap.munchbox.domain.core.UsuarioPerfil;
import br.com.fiap.munchbox.infrastructure.entity.UsuarioPerfilEntity;
import org.springframework.stereotype.Component;

@Component
public class UsuarioPerfilMapper
{
    public static UsuarioPerfil toDomain(UsuarioPerfilEntity usuarioPerfilEntity)
    {
        return new UsuarioPerfil(usuarioPerfilEntity.getId(), usuarioPerfilEntity.getNome(), usuarioPerfilEntity.getDataAtualizacao(), usuarioPerfilEntity.getDataInclusao());
    }

    public static UsuarioPerfilEntity toEntity(UsuarioPerfil usuarioPerfil)
    {
        return new UsuarioPerfilEntity(
                usuarioPerfil.getId(),
                usuarioPerfil.getNome(),
                usuarioPerfil.getDataAtualizacao(),
                usuarioPerfil.getDataInclusao());
    }
}
