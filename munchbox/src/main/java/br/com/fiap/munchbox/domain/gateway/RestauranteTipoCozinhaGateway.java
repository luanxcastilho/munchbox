package br.com.fiap.munchbox.domain.gateway;

import br.com.fiap.munchbox.domain.core.RestauranteTipoCozinha;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface RestauranteTipoCozinhaGateway
{
    void create(RestauranteTipoCozinha restauranteTipoCozinha);

    void update(RestauranteTipoCozinha restauranteTipoCozinha);

    void delete(Long id);

    Optional<RestauranteTipoCozinha> findById(Long id);

    List<RestauranteTipoCozinha> findAll(Pageable pageable);
}
