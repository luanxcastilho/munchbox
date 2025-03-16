package br.com.fiap.munchbox.infrastructure.mapper;

import br.com.fiap.munchbox.domain.core.RestauranteTipoCozinha;
import br.com.fiap.munchbox.infrastructure.entity.RestauranteTipoCozinhaEntity;
import org.springframework.stereotype.Component;

@Component
public class RestauranteTipoCozinhaMapper
{
    public static RestauranteTipoCozinha toDomain(RestauranteTipoCozinhaEntity restauranteTipoCozinhaEntity)
    {
        return new RestauranteTipoCozinha(restauranteTipoCozinhaEntity.getId(),
                                          restauranteTipoCozinhaEntity.getNome(),
                                          restauranteTipoCozinhaEntity.getDataAtualizacao(),
                                          restauranteTipoCozinhaEntity.getDataInclusao());
    }

    public static RestauranteTipoCozinhaEntity toEntity(RestauranteTipoCozinha restauranteTipoCozinha)
    {
        return new RestauranteTipoCozinhaEntity(restauranteTipoCozinha.getId(),
                                                restauranteTipoCozinha.getNome(),
                                                restauranteTipoCozinha.getDataAtualizacao(),
                                                restauranteTipoCozinha.getDataInclusao());
    }

}
