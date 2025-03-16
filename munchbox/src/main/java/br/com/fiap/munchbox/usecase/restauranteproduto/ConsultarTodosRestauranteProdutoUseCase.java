package br.com.fiap.munchbox.usecase.restauranteproduto;

import br.com.fiap.munchbox.domain.core.RestauranteProduto;
import br.com.fiap.munchbox.domain.gateway.RestauranteProdutoGateway;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ConsultarTodosRestauranteProdutoUseCase
{
    private final RestauranteProdutoGateway restauranteProdutoGateway;

    public ConsultarTodosRestauranteProdutoUseCase(RestauranteProdutoGateway restauranteProdutoGateway)
    {
        this.restauranteProdutoGateway = restauranteProdutoGateway;
    }

    public List<RestauranteProduto> execute(Pageable pageable)
    {
        return this.restauranteProdutoGateway.findAll(pageable);
    }
}
