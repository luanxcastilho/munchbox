package br.com.fiap.munchbox.usecase.restaurantetipocozinha;

import br.com.fiap.munchbox.domain.gateway.RestauranteTipoCozinhaGateway;
import org.springframework.stereotype.Component;

@Component
public class RemoverRestauranteTipoCozinhaUseCase
{
    private final RestauranteTipoCozinhaGateway restauranteTipoCozinhaGateway;

    public RemoverRestauranteTipoCozinhaUseCase(RestauranteTipoCozinhaGateway restauranteTipoCozinhaGateway)
    {
        this.restauranteTipoCozinhaGateway = restauranteTipoCozinhaGateway;
    }

    public void execute(Long id)
    {
        this.restauranteTipoCozinhaGateway.delete(id);
    }
}
