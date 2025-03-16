package br.com.fiap.munchbox.infrastructure.repository;

import br.com.fiap.munchbox.infrastructure.entity.ClienteEnderecoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteEnderecoRepositoryJpa extends JpaRepository<ClienteEnderecoEntity, Long>
{
}
