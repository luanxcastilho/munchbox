package br.com.fiap.munchbox.usecase.restauranteproduto;

import br.com.fiap.munchbox.domain.core.RestauranteProduto;
import br.com.fiap.munchbox.domain.gateway.RestauranteProdutoGateway;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ConsultarUmRestauranteProdutoUseCase
{
    private final RestauranteProdutoGateway restauranteProdutoGateway;

    public ConsultarUmRestauranteProdutoUseCase(RestauranteProdutoGateway restauranteProdutoGateway)
    {
        this.restauranteProdutoGateway = restauranteProdutoGateway;
    }

    public Optional<RestauranteProduto> execute(Long id)
    {
        return this.restauranteProdutoGateway.findById(id);
    }
}
