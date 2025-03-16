package br.com.fiap.munchbox.infrastructure.repository;

import br.com.fiap.munchbox.infrastructure.entity.RestauranteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestauranteRepositoryJpa extends JpaRepository<RestauranteEntity, Long>
{
}
