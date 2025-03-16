package br.com.fiap.munchbox.infrastructure.mapper;

import br.com.fiap.munchbox.domain.core.RestauranteFuncionamento;
import br.com.fiap.munchbox.infrastructure.entity.RestauranteFuncionamentoEntity;

public class RestauranteFuncionamentoMapper {

    public static RestauranteFuncionamento toDomain(RestauranteFuncionamentoEntity restauranteFuncionamentoEntity) {
        return new RestauranteFuncionamento(restauranteFuncionamentoEntity.getId(),
                                            RestauranteMapper.toDomain(restauranteFuncionamentoEntity.getRestauranteEntity()),
                                            restauranteFuncionamentoEntity.getDiaDaSemana(),
                                            restauranteFuncionamentoEntity.getHorarioAbertura(),
                                            restauranteFuncionamentoEntity.getHorarioFechamento(),
                                            restauranteFuncionamentoEntity.getDataAtualizacao(),
                                            restauranteFuncionamentoEntity.getDataInclusao());
    }

    public static RestauranteFuncionamentoEntity toEntity(RestauranteFuncionamento restauranteFuncionamento) {
        return new RestauranteFuncionamentoEntity(restauranteFuncionamento.getId(),
                                                  RestauranteMapper.toEntity(restauranteFuncionamento.getRestaurante()),
                                                  restauranteFuncionamento.getDiaDaSemana(),
                                                  restauranteFuncionamento.getHorarioAbertura(),
                                                  restauranteFuncionamento.getHorarioFechamento(),
                                                  restauranteFuncionamento.getDataAtualizacao(),
                                                  restauranteFuncionamento.getDataInclusao());
    }
}
