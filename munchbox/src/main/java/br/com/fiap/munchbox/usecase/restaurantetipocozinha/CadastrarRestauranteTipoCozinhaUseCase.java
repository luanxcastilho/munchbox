package br.com.fiap.munchbox.usecase.restaurantetipocozinha;

import br.com.fiap.munchbox.domain.core.RestauranteTipoCozinha;
import br.com.fiap.munchbox.domain.gateway.RestauranteTipoCozinhaGateway;
import org.springframework.stereotype.Component;

@Component
public class CadastrarRestauranteTipoCozinhaUseCase
{
    private final RestauranteTipoCozinhaGateway restauranteTipoCozinhaGateway;

    public CadastrarRestauranteTipoCozinhaUseCase(RestauranteTipoCozinhaGateway restauranteTipoCozinhaGateway)
    {
        this.restauranteTipoCozinhaGateway = restauranteTipoCozinhaGateway;
    }

    public void execute(RestauranteTipoCozinha restauranteTipoCozinha)
    {
        this.restauranteTipoCozinhaGateway.create(restauranteTipoCozinha);
    }
}
