package br.com.fiap.munchbox.infrastructure.repository;

import br.com.fiap.munchbox.domain.core.Restaurante;
import br.com.fiap.munchbox.domain.gateway.RestauranteGateway;
import br.com.fiap.munchbox.infrastructure.mapper.RestauranteMapper;
import br.com.fiap.munchbox.infrastructure.entity.RestauranteEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class RestauranteRepository implements RestauranteGateway
{
    private final RestauranteRepositoryJpa restauranteRepositoryJpa;

    public RestauranteRepository(RestauranteRepositoryJpa restauranteRepositoryJpa)
    {
        this.restauranteRepositoryJpa = restauranteRepositoryJpa;
    }

    @Override
    public void create(Restaurante restaurante)
    {
        RestauranteEntity restauranteEntity = RestauranteMapper.toEntity(restaurante);
        restauranteRepositoryJpa.save(restauranteEntity);
    }

    @Override
    public void update(Restaurante restaurante)
    {
        RestauranteEntity restauranteEntity = RestauranteMapper.toEntity(restaurante);
        restauranteRepositoryJpa.save(restauranteEntity);
    }

    @Override
    public void delete(Long id)
    {
        restauranteRepositoryJpa.deleteById(id);
    }

    @Override
    public Optional<Restaurante> findById(Long id)
    {
        return restauranteRepositoryJpa.findById(id)
                .map(RestauranteMapper::toDomain);
    }


    @Override
    public List<Restaurante> findAll(Pageable pageable)
    {
        List<RestauranteEntity> restauranteEntities = restauranteRepositoryJpa.findAll(pageable)
                .getContent();

        return restauranteEntities.stream()
                .map(RestauranteMapper::toDomain)
                .collect(Collectors.toList());
    }
}
