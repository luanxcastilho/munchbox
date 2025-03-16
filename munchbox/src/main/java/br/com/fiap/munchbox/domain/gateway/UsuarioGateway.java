package br.com.fiap.munchbox.domain.gateway;

import br.com.fiap.munchbox.domain.core.Usuario;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface UsuarioGateway
{
    void create(Usuario usuario);
    void update(Usuario usuario);
    void delete(Long id);
    Optional<Usuario> findById(Long id);
    List<Usuario> findAll(Pageable pageable);
}
