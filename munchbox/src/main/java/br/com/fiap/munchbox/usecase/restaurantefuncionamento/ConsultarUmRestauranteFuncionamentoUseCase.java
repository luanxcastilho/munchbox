package br.com.fiap.munchbox.usecase.restaurantefuncionamento;

import br.com.fiap.munchbox.domain.core.RestauranteFuncionamento;
import br.com.fiap.munchbox.domain.gateway.RestauranteFuncionamentoGateway;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ConsultarUmRestauranteFuncionamentoUseCase
{
    private final RestauranteFuncionamentoGateway restauranteFuncionamentoGateway;

    public ConsultarUmRestauranteFuncionamentoUseCase(RestauranteFuncionamentoGateway restauranteFuncionamentoGateway)
    {
        this.restauranteFuncionamentoGateway = restauranteFuncionamentoGateway;
    }

    public Optional<RestauranteFuncionamento> execute(Long id)
    {
        return this.restauranteFuncionamentoGateway.findById(id);
    }
}
