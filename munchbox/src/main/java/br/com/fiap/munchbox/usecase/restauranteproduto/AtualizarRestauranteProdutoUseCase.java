package br.com.fiap.munchbox.usecase.restauranteproduto;

import br.com.fiap.munchbox.domain.core.RestauranteProduto;
import br.com.fiap.munchbox.domain.gateway.RestauranteProdutoGateway;
import org.springframework.stereotype.Component;

@Component
public class AtualizarRestauranteProdutoUseCase {
    private final RestauranteProdutoGateway restauranteProdutoGateway;

    public AtualizarRestauranteProdutoUseCase(RestauranteProdutoGateway restauranteProdutoGateway) {
        this.restauranteProdutoGateway = restauranteProdutoGateway;
    }

    public void execute(RestauranteProduto restauranteProduto) {
        this.restauranteProdutoGateway.update(restauranteProduto);
    }
}
