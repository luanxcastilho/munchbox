package br.com.fiap.munchbox.usecase.restaurantetipocozinha;

import br.com.fiap.munchbox.domain.core.RestauranteTipoCozinha;
import br.com.fiap.munchbox.domain.gateway.RestauranteTipoCozinhaGateway;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ConsultarUmRestauranteTipoCozinhaUseCase
{
    private final RestauranteTipoCozinhaGateway restauranteTipoCozinhaGateway;

    public ConsultarUmRestauranteTipoCozinhaUseCase(RestauranteTipoCozinhaGateway restauranteTipoCozinhaGateway)
    {
        this.restauranteTipoCozinhaGateway = restauranteTipoCozinhaGateway;
    }

    public Optional<RestauranteTipoCozinha> execute(Long id)
    {
        return this.restauranteTipoCozinhaGateway.findById(id);
    }
}
