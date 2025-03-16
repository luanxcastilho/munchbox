package br.com.fiap.munchbox.domain.gateway;

import br.com.fiap.munchbox.domain.core.UsuarioPerfil;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface UsuarioPerfilGateway
{
    void create(UsuarioPerfil usuarioPerfil);
    void update(UsuarioPerfil usuarioPerfil);
    void delete(Long id);
    Optional<UsuarioPerfil> findById(Long id);
    List<UsuarioPerfil> findAll(Pageable pageable);
}
