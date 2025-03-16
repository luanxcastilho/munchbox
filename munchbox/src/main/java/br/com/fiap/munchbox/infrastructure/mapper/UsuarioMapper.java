package br.com.fiap.munchbox.infrastructure.mapper;

import br.com.fiap.munchbox.domain.core.Usuario;
import br.com.fiap.munchbox.infrastructure.entity.UsuarioEntity;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {

    public static Usuario toDomain(UsuarioEntity usuarioEntity) {
        return new Usuario(
                usuarioEntity.getId(),
                usuarioEntity.getLogin(),
                usuarioEntity.getSenha(),
                usuarioEntity.getDataAtualizacao(),
                usuarioEntity.getDataInclusao(),
                UsuarioPerfilMapper.toDomain(usuarioEntity.getUsuarioPerfilEntity())
        );
    }

    public static UsuarioEntity toEntity(Usuario usuario) {
        return new UsuarioEntity(
                usuario.getId(),
                usuario.getLogin(),
                usuario.getSenha(),
                usuario.getDataAtualizacao(),
                usuario.getDataInclusao(),
                UsuarioPerfilMapper.toEntity(usuario.getUsuarioPerfil())
        );
    }
}