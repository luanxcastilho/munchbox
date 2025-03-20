package br.com.fiap.munchbox.usecase.restaurantefuncionamento;

import br.com.fiap.munchbox.domain.core.RestauranteFuncionamento;
import br.com.fiap.munchbox.domain.gateway.RestauranteFuncionamentoGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

class CadastrarRestauranteFuncionamentoUseCaseTest {

    @Mock
    private RestauranteFuncionamentoGateway restauranteFuncionamentoGateway;

    @InjectMocks
    private CadastrarRestauranteFuncionamentoUseCase cadastrarRestauranteFuncionamentoUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveCadastrarRestauranteFuncionamento() {
        RestauranteFuncionamento restauranteFuncionamento = new RestauranteFuncionamento();
        cadastrarRestauranteFuncionamentoUseCase.execute(restauranteFuncionamento);

        verify(restauranteFuncionamentoGateway).create(restauranteFuncionamento);
    }
}
