package br.com.fiap.munchbox.usecase.restaurantefuncionamento;

import br.com.fiap.munchbox.domain.gateway.RestauranteFuncionamentoGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

class RemoverRestauranteFuncionamentoUseCaseTest {

    @Mock
    private RestauranteFuncionamentoGateway restauranteFuncionamentoGateway;

    @InjectMocks
    private RemoverRestauranteFuncionamentoUseCase removerRestauranteFuncionamentoUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveRemoverRestauranteFuncionamento() {
        Long id = 1L;

        removerRestauranteFuncionamentoUseCase.execute(id);

        verify(restauranteFuncionamentoGateway).delete(id);
    }
}
