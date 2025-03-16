package br.com.fiap.munchbox.infrastructure.mapper;

import br.com.fiap.munchbox.domain.core.RestauranteProduto;
import br.com.fiap.munchbox.infrastructure.entity.RestauranteProdutoEntity;

public class RestauranteProdutoMapper {

    public static RestauranteProduto toDomain(RestauranteProdutoEntity restauranteProdutoEntity) {
        return new RestauranteProduto(restauranteProdutoEntity.getId(),
                                      RestauranteMapper.toDomain(restauranteProdutoEntity.getRestauranteEntity()),
                                      restauranteProdutoEntity.getNome(),
                                      restauranteProdutoEntity.getDescricao(),
                                      restauranteProdutoEntity.getValor(),
                                      restauranteProdutoEntity.getImagem(),
                                      restauranteProdutoEntity.getPermiteEntrega(),
                                      restauranteProdutoEntity.getDataAtualizacao(),
                                      restauranteProdutoEntity.getDataInclusao());
    }

    public static RestauranteProdutoEntity toEntity(RestauranteProduto restauranteProduto) {
        return new RestauranteProdutoEntity(restauranteProduto.getId(),
                                            RestauranteMapper.toEntity(restauranteProduto.getRestaurante()),
                                            restauranteProduto.getNome(),
                                            restauranteProduto.getDescricao(),
                                            restauranteProduto.getValor(),
                                            restauranteProduto.getImagem(),
                                            restauranteProduto.getPermiteEntrega(),
                                            restauranteProduto.getDataAtualizacao(),
                                            restauranteProduto.getDataInclusao());
    }
}
