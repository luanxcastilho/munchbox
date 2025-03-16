package br.com.fiap.munchbox.usecase.restaurantefuncionamento;

import br.com.fiap.munchbox.domain.gateway.RestauranteFuncionamentoGateway;
import org.springframework.stereotype.Component;

@Component
public class RemoverRestauranteFuncionamentoUseCase {

    private final RestauranteFuncionamentoGateway restauranteFuncionamentoGateway;

    public RemoverRestauranteFuncionamentoUseCase(RestauranteFuncionamentoGateway restauranteFuncionamentoGateway) {
        this.restauranteFuncionamentoGateway = restauranteFuncionamentoGateway;
    }

    public void execute(Long id) {
        this.restauranteFuncionamentoGateway.delete(id);
    }
}
