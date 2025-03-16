package br.com.fiap.munchbox.domain.gateway;

import br.com.fiap.munchbox.domain.core.RestauranteFuncionamento;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface RestauranteFuncionamentoGateway {

    void create(RestauranteFuncionamento restauranteFuncionamento);

    void update(RestauranteFuncionamento restauranteFuncionamento);

    void delete(Long id);

    Optional<RestauranteFuncionamento> findById(Long id);

    List<RestauranteFuncionamento> findAll(Pageable pageable);
}
