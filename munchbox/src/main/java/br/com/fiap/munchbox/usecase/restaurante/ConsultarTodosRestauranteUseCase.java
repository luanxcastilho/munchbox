package br.com.fiap.munchbox.usecase.restaurante;

import br.com.fiap.munchbox.domain.core.Restaurante;
import br.com.fiap.munchbox.domain.gateway.RestauranteGateway;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ConsultarTodosRestauranteUseCase
{
    private final RestauranteGateway restauranteGateway;

    public ConsultarTodosRestauranteUseCase(RestauranteGateway restauranteGateway)
    {
        this.restauranteGateway = restauranteGateway;
    }

    public List<Restaurante> execute(Pageable pageable)
    {
        return this.restauranteGateway.findAll(pageable);
    }
}
