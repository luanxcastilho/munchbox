package br.com.fiap.munchbox.infrastructure.repository;

import br.com.fiap.munchbox.domain.core.RestauranteTipoCozinha;
import br.com.fiap.munchbox.domain.gateway.RestauranteTipoCozinhaGateway;
import br.com.fiap.munchbox.infrastructure.mapper.RestauranteTipoCozinhaMapper;
import br.com.fiap.munchbox.infrastructure.entity.RestauranteTipoCozinhaEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class RestauranteTipoCozinhaRepository implements RestauranteTipoCozinhaGateway
{
    private final RestauranteTipoCozinhaRepositoryJpa restauranteTipoCozinhaRepositoryJpa;

    public RestauranteTipoCozinhaRepository(RestauranteTipoCozinhaRepositoryJpa restauranteTipoCozinhaRepositoryJpa)
    {
        this.restauranteTipoCozinhaRepositoryJpa = restauranteTipoCozinhaRepositoryJpa;
    }

    @Override
    public void create(RestauranteTipoCozinha restauranteTipoCozinha)
    {
        RestauranteTipoCozinhaEntity restauranteTipoCozinhaEntity = RestauranteTipoCozinhaMapper.toEntity(restauranteTipoCozinha);
        restauranteTipoCozinhaRepositoryJpa.save(restauranteTipoCozinhaEntity);
    }

    @Override
    public void update(RestauranteTipoCozinha restauranteTipoCozinha)
    {
        RestauranteTipoCozinhaEntity restauranteTipoCozinhaEntity = RestauranteTipoCozinhaMapper.toEntity(restauranteTipoCozinha);
        restauranteTipoCozinhaRepositoryJpa.save(restauranteTipoCozinhaEntity);
    }

    @Override
    public void delete(Long id)
    {
        restauranteTipoCozinhaRepositoryJpa.deleteById(id);
    }

    @Override
    public Optional<RestauranteTipoCozinha> findById(Long id) {
        return restauranteTipoCozinhaRepositoryJpa.findById(id).map(RestauranteTipoCozinhaMapper::toDomain);
    }


    @Override
    public List<RestauranteTipoCozinha> findAll(Pageable pageable)
    {
        List<RestauranteTipoCozinhaEntity> restauranteTipoCozinhaEntities = restauranteTipoCozinhaRepositoryJpa.findAll(pageable).getContent();
        return restauranteTipoCozinhaEntities.stream().map(RestauranteTipoCozinhaMapper::toDomain).collect(Collectors.toList());
    }
}
