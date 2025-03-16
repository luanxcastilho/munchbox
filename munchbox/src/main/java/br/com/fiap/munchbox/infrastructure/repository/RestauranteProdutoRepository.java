package br.com.fiap.munchbox.infrastructure.repository;

import br.com.fiap.munchbox.domain.core.RestauranteProduto;
import br.com.fiap.munchbox.domain.gateway.RestauranteProdutoGateway;
import br.com.fiap.munchbox.infrastructure.entity.RestauranteProdutoEntity;
import br.com.fiap.munchbox.infrastructure.mapper.RestauranteProdutoMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class RestauranteProdutoRepository implements RestauranteProdutoGateway {

    private final RestauranteProdutoRepositoryJpa restauranteProdutoRepositoryJpa;

    public RestauranteProdutoRepository(RestauranteProdutoRepositoryJpa restauranteProdutoRepositoryJpa) {
        this.restauranteProdutoRepositoryJpa = restauranteProdutoRepositoryJpa;
    }

    @Override
    public void create(RestauranteProduto restauranteProduto) {
        RestauranteProdutoEntity restauranteProdutoEntity = RestauranteProdutoMapper.toEntity(restauranteProduto);
        restauranteProdutoRepositoryJpa.save(restauranteProdutoEntity);
    }

    @Override
    public void update(RestauranteProduto restauranteProduto) {
        RestauranteProdutoEntity restauranteProdutoEntity = RestauranteProdutoMapper.toEntity(restauranteProduto);
        restauranteProdutoRepositoryJpa.save(restauranteProdutoEntity);
    }

    @Override
    public void delete(Long id) {
        restauranteProdutoRepositoryJpa.deleteById(id);
    }

    @Override
    public Optional<RestauranteProduto> findById(Long id) {
        return restauranteProdutoRepositoryJpa.findById(id).map(RestauranteProdutoMapper::toDomain);
    }


    @Override
    public List<RestauranteProduto> findAll(Pageable pageable) {
        List<RestauranteProdutoEntity> restauranteProdutoEntities = restauranteProdutoRepositoryJpa.findAll(pageable)
                .getContent();
        return restauranteProdutoEntities.stream().map(RestauranteProdutoMapper::toDomain).collect(Collectors.toList());
    }
}
