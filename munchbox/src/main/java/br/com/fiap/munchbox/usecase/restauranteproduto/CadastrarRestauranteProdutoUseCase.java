package br.com.fiap.munchbox.usecase.restauranteproduto;

import br.com.fiap.munchbox.domain.core.RestauranteProduto;
import br.com.fiap.munchbox.domain.gateway.RestauranteProdutoGateway;
import org.springframework.stereotype.Component;

@Component
public class CadastrarRestauranteProdutoUseCase
{
    private final RestauranteProdutoGateway restauranteProdutoGateway;

    public CadastrarRestauranteProdutoUseCase(RestauranteProdutoGateway restauranteProdutoGateway)
    {
        this.restauranteProdutoGateway = restauranteProdutoGateway;
    }

    public void execute(RestauranteProduto restauranteProduto)
    {
        this.restauranteProdutoGateway.create(restauranteProduto);
    }
}
