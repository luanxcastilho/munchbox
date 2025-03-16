package br.com.fiap.munchbox.domain.gateway;

import br.com.fiap.munchbox.domain.core.RestauranteProduto;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface RestauranteProdutoGateway {
    void create(RestauranteProduto restauranteProduto);
    void update(RestauranteProduto restauranteProduto);
    void delete(Long id);
    Optional<RestauranteProduto> findById(Long id);
    List<RestauranteProduto> findAll(Pageable pageable);
}
