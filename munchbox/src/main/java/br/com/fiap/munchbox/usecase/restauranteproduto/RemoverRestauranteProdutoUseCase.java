package br.com.fiap.munchbox.usecase.restauranteproduto;

import br.com.fiap.munchbox.domain.gateway.RestauranteProdutoGateway;
import org.springframework.stereotype.Component;

@Component
public class RemoverRestauranteProdutoUseCase {

    private final RestauranteProdutoGateway restauranteProdutoGateway;

    public RemoverRestauranteProdutoUseCase(RestauranteProdutoGateway restauranteProdutoGateway) {
        this.restauranteProdutoGateway = restauranteProdutoGateway;
    }

    public void execute(Long id) {
        this.restauranteProdutoGateway.delete(id);
    }
}
