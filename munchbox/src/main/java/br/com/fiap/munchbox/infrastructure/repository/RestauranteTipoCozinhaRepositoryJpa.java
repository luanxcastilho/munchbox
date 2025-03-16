package br.com.fiap.munchbox.infrastructure.repository;

import br.com.fiap.munchbox.infrastructure.entity.RestauranteTipoCozinhaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestauranteTipoCozinhaRepositoryJpa extends JpaRepository<RestauranteTipoCozinhaEntity, Long>
{
}
