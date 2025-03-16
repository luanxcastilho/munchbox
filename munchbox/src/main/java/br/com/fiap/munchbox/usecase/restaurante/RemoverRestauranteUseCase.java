package br.com.fiap.munchbox.usecase.restaurante;

import br.com.fiap.munchbox.domain.gateway.RestauranteGateway;
import org.springframework.stereotype.Component;

@Component
public class RemoverRestauranteUseCase
{
    private final RestauranteGateway restauranteGateway;

    public RemoverRestauranteUseCase(RestauranteGateway restauranteGateway)
    {
        this.restauranteGateway = restauranteGateway;
    }

    public void execute(Long id)
    {
        this.restauranteGateway.delete(id);
    }
}
