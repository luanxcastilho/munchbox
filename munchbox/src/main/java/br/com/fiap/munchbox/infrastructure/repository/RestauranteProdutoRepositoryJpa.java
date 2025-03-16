package br.com.fiap.munchbox.infrastructure.repository;

import br.com.fiap.munchbox.infrastructure.entity.RestauranteProdutoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestauranteProdutoRepositoryJpa extends JpaRepository<RestauranteProdutoEntity, Long> {
}
