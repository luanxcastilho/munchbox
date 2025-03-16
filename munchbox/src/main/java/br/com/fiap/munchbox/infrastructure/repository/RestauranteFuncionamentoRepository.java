package br.com.fiap.munchbox.infrastructure.repository;

import br.com.fiap.munchbox.domain.core.RestauranteFuncionamento;
import br.com.fiap.munchbox.domain.gateway.RestauranteFuncionamentoGateway;
import br.com.fiap.munchbox.infrastructure.entity.RestauranteFuncionamentoEntity;
import br.com.fiap.munchbox.infrastructure.mapper.RestauranteFuncionamentoMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class RestauranteFuncionamentoRepository implements RestauranteFuncionamentoGateway
{
    private final RestauranteFuncionamentoRepositoryJpa restauranteFuncionamentoRepositoryJpa;

    public RestauranteFuncionamentoRepository(RestauranteFuncionamentoRepositoryJpa restauranteFuncionamentoRepositoryJpa)
    {
        this.restauranteFuncionamentoRepositoryJpa = restauranteFuncionamentoRepositoryJpa;
    }

    @Override
    public void create(RestauranteFuncionamento restauranteFuncionamento)
    {
        RestauranteFuncionamentoEntity restauranteFuncionamentoEntity = RestauranteFuncionamentoMapper.toEntity(restauranteFuncionamento);
        restauranteFuncionamentoRepositoryJpa.save(restauranteFuncionamentoEntity);
    }

    @Override
    public void update(RestauranteFuncionamento restauranteFuncionamento)
    {
        RestauranteFuncionamentoEntity restauranteFuncionamentoEntity = RestauranteFuncionamentoMapper.toEntity(restauranteFuncionamento);
        restauranteFuncionamentoRepositoryJpa.save(restauranteFuncionamentoEntity);
    }

    @Override
    public void delete(Long id)
    {
        restauranteFuncionamentoRepositoryJpa.deleteById(id);
    }

    @Override
    public Optional<RestauranteFuncionamento> findById(Long id) {
        return restauranteFuncionamentoRepositoryJpa.findById(id).map(RestauranteFuncionamentoMapper::toDomain);
    }


    @Override
    public List<RestauranteFuncionamento> findAll(Pageable pageable)
    {
        List<RestauranteFuncionamentoEntity> restauranteFuncionamentoEntities = restauranteFuncionamentoRepositoryJpa.findAll(pageable).getContent();
        return restauranteFuncionamentoEntities.stream().map(RestauranteFuncionamentoMapper::toDomain).collect(Collectors.toList());
    }
}
