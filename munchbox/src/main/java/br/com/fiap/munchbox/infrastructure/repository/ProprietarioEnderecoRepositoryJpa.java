package br.com.fiap.munchbox.infrastructure.repository;

import br.com.fiap.munchbox.infrastructure.entity.ProprietarioEnderecoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProprietarioEnderecoRepositoryJpa extends JpaRepository<ProprietarioEnderecoEntity, Long>
{
}
