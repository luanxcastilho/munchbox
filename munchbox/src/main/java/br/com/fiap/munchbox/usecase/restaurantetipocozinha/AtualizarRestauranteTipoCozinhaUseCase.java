package br.com.fiap.munchbox.usecase.restaurantetipocozinha;

import br.com.fiap.munchbox.domain.core.RestauranteTipoCozinha;
import br.com.fiap.munchbox.domain.gateway.RestauranteTipoCozinhaGateway;
import org.springframework.stereotype.Component;

@Component
public class AtualizarRestauranteTipoCozinhaUseCase
{
    private final RestauranteTipoCozinhaGateway restauranteTipoCozinhaGateway;

    public AtualizarRestauranteTipoCozinhaUseCase(RestauranteTipoCozinhaGateway restauranteTipoCozinhaGateway)
    {
        this.restauranteTipoCozinhaGateway = restauranteTipoCozinhaGateway;
    }

    public void execute(RestauranteTipoCozinha restauranteTipoCozinha)
    {
        this.restauranteTipoCozinhaGateway.update(restauranteTipoCozinha);
    }
}
