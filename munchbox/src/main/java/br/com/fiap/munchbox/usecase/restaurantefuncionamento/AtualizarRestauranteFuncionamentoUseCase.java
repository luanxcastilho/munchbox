package br.com.fiap.munchbox.usecase.restaurantefuncionamento;

import br.com.fiap.munchbox.domain.core.RestauranteFuncionamento;
import br.com.fiap.munchbox.domain.gateway.RestauranteFuncionamentoGateway;
import org.springframework.stereotype.Component;

@Component
public class AtualizarRestauranteFuncionamentoUseCase {
    private final RestauranteFuncionamentoGateway restauranteFuncionamentoGateway;

    public AtualizarRestauranteFuncionamentoUseCase(RestauranteFuncionamentoGateway restauranteFuncionamentoGateway) {
        this.restauranteFuncionamentoGateway = restauranteFuncionamentoGateway;
    }

    public void execute(RestauranteFuncionamento restauranteFuncionamento) {
        this.restauranteFuncionamentoGateway.update(restauranteFuncionamento);
    }
}
