package br.com.fiap.munchbox.infrastructure.repository;

import br.com.fiap.munchbox.domain.core.Usuario;
import br.com.fiap.munchbox.domain.gateway.UsuarioGateway;
import br.com.fiap.munchbox.infrastructure.mapper.UsuarioMapper;
import br.com.fiap.munchbox.infrastructure.entity.UsuarioEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class UsuarioRepository implements UsuarioGateway
{
    private final UsuarioRepositoryJpa usuarioRepositoryJpa;

    public UsuarioRepository(UsuarioRepositoryJpa usuarioRepositoryJpa)
    {
        this.usuarioRepositoryJpa = usuarioRepositoryJpa;
    }

    @Override
    public void create(Usuario usuario)
    {
        UsuarioEntity usuarioEntity = UsuarioMapper.toEntity(usuario);
        usuarioRepositoryJpa.save(usuarioEntity);
    }

    @Override
    public void update(Usuario usuario)
    {
        UsuarioEntity usuarioEntity = UsuarioMapper.toEntity(usuario);
        usuarioRepositoryJpa.save(usuarioEntity);
    }

    @Override
    public void delete(Long id)
    {
        usuarioRepositoryJpa.deleteById(id);
    }

    @Override
    public Optional<Usuario> findById(Long id)
    {
        return usuarioRepositoryJpa.findById(id).map(UsuarioMapper::toDomain);
    }

    @Override
    public List<Usuario> findAll(Pageable pageable)
    {
        List<UsuarioEntity> usuarioEntities = usuarioRepositoryJpa.findAll(pageable).getContent();
        return usuarioEntities.stream().map(UsuarioMapper::toDomain).collect(Collectors.toList());
    }
}
