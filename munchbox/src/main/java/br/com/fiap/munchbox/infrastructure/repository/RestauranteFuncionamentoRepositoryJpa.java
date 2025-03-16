package br.com.fiap.munchbox.infrastructure.repository;

import br.com.fiap.munchbox.infrastructure.entity.RestauranteFuncionamentoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestauranteFuncionamentoRepositoryJpa extends JpaRepository<RestauranteFuncionamentoEntity, Long> {
}
