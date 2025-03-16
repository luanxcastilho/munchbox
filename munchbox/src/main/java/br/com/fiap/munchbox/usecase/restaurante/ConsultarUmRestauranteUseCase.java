package br.com.fiap.munchbox.usecase.restaurante;

import br.com.fiap.munchbox.domain.core.Restaurante;
import br.com.fiap.munchbox.domain.gateway.RestauranteGateway;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ConsultarUmRestauranteUseCase
{
    private final RestauranteGateway restauranteGateway;

    public ConsultarUmRestauranteUseCase(RestauranteGateway restauranteGateway)
    {
        this.restauranteGateway = restauranteGateway;
    }

    public Optional<Restaurante> execute(Long id)
    {
        return this.restauranteGateway.findById(id);
    }
}
