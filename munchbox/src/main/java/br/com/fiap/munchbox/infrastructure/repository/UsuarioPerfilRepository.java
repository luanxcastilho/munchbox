package br.com.fiap.munchbox.infrastructure.repository;

import br.com.fiap.munchbox.domain.core.UsuarioPerfil;
import br.com.fiap.munchbox.domain.gateway.UsuarioPerfilGateway;
import br.com.fiap.munchbox.infrastructure.mapper.UsuarioPerfilMapper;
import br.com.fiap.munchbox.infrastructure.entity.UsuarioPerfilEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class UsuarioPerfilRepository implements UsuarioPerfilGateway
{
    private final UsuarioPerfilRepositoryJpa usuarioPerfilRepositoryJpa;

    public UsuarioPerfilRepository(UsuarioPerfilRepositoryJpa usuarioPerfilRepositoryJpa)
    {
        this.usuarioPerfilRepositoryJpa = usuarioPerfilRepositoryJpa;
    }

    @Override
    public void create(UsuarioPerfil usuarioPerfil)
    {
        UsuarioPerfilEntity usuarioPerfilEntity = UsuarioPerfilMapper.toEntity(usuarioPerfil);
        usuarioPerfilRepositoryJpa.save(usuarioPerfilEntity);
    }

    @Override
    public void update(UsuarioPerfil usuarioPerfil)
    {
        UsuarioPerfilEntity usuarioPerfilEntity = UsuarioPerfilMapper.toEntity(usuarioPerfil);
        usuarioPerfilRepositoryJpa.save(usuarioPerfilEntity);
    }

    @Override
    public void delete(Long id)
    {
        usuarioPerfilRepositoryJpa.deleteById(id);
    }

    @Override
    public Optional<UsuarioPerfil> findById(Long id)
    {
        return usuarioPerfilRepositoryJpa.findById(id).map(UsuarioPerfilMapper::toDomain);
    }

    @Override
    public List<UsuarioPerfil> findAll(Pageable pageable)
    {
        List<UsuarioPerfilEntity> usuarioPerfilEntities = this.usuarioPerfilRepositoryJpa.findAll(pageable).getContent();
        return usuarioPerfilEntities.stream().map(UsuarioPerfilMapper::toDomain).collect(Collectors.toList());
    }
}
