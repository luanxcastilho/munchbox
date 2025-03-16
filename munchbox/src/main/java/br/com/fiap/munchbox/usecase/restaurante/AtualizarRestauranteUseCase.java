package br.com.fiap.munchbox.usecase.restaurante;

import br.com.fiap.munchbox.domain.core.Restaurante;
import br.com.fiap.munchbox.domain.gateway.RestauranteGateway;
import org.springframework.stereotype.Component;

@Component
public class AtualizarRestauranteUseCase {
    private final RestauranteGateway restauranteGateway;

    public AtualizarRestauranteUseCase(RestauranteGateway restauranteGateway) {
        this.restauranteGateway = restauranteGateway;
    }

    public void execute(Restaurante restaurante) {
        this.restauranteGateway.update(restaurante);
    }
}
