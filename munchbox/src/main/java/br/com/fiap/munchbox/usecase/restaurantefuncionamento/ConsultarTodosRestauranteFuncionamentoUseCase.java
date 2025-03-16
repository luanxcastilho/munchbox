package br.com.fiap.munchbox.usecase.restaurantefuncionamento;

import br.com.fiap.munchbox.domain.core.RestauranteFuncionamento;
import br.com.fiap.munchbox.domain.gateway.RestauranteFuncionamentoGateway;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ConsultarTodosRestauranteFuncionamentoUseCase
{
    private final RestauranteFuncionamentoGateway restauranteFuncionamentoGateway;

    public ConsultarTodosRestauranteFuncionamentoUseCase(RestauranteFuncionamentoGateway restauranteFuncionamentoGateway)
    {
        this.restauranteFuncionamentoGateway = restauranteFuncionamentoGateway;
    }

    public List<RestauranteFuncionamento> execute(Pageable pageable)
    {
        return this.restauranteFuncionamentoGateway.findAll(pageable);
    }
}
