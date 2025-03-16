package br.com.fiap.munchbox.usecase.restaurantetipocozinha;

import br.com.fiap.munchbox.domain.core.RestauranteTipoCozinha;
import br.com.fiap.munchbox.domain.gateway.RestauranteTipoCozinhaGateway;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ConsultarTodosRestauranteTipoCozinhaUseCase
{
    private final RestauranteTipoCozinhaGateway restauranteTipoCozinhaGateway;

    public ConsultarTodosRestauranteTipoCozinhaUseCase(RestauranteTipoCozinhaGateway restauranteTipoCozinhaGateway)
    {
        this.restauranteTipoCozinhaGateway = restauranteTipoCozinhaGateway;
    }

    public List<RestauranteTipoCozinha> execute(Pageable pageable)
    {
        return this.restauranteTipoCozinhaGateway.findAll(pageable);
    }
}
