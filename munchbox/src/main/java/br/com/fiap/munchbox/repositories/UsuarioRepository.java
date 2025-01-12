package br.com.fiap.munchbox.repositories;

import br.com.fiap.munchbox.dtos.usuario.UsuarioRequestDTO;
import br.com.fiap.munchbox.entities.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository {

    Optional<Usuario> findById(Long id);

    List<Usuario> findAll(int size, int offset);

    Integer create(UsuarioRequestDTO usuario);

    Integer update(UsuarioRequestDTO usuario, Long id);

    Integer delete(Long id);

    Optional<Usuario> findByLogin(String login);

}
