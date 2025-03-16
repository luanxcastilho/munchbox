package br.com.fiap.munchbox.infrastructure.repository;

import br.com.fiap.munchbox.infrastructure.entity.UsuarioPerfilEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioPerfilRepositoryJpa extends JpaRepository<UsuarioPerfilEntity, Long>
{
}
