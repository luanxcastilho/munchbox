package br.com.fiap.munchbox.usecase.restauranteproduto;

import br.com.fiap.munchbox.domain.core.RestauranteProduto;
import br.com.fiap.munchbox.domain.gateway.RestauranteProdutoGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

class CadastrarRestauranteProdutoUseCaseTest {

    @Mock
    private RestauranteProdutoGateway restauranteProdutoGateway;

    @InjectMocks
    private CadastrarRestauranteProdutoUseCase cadastrarRestauranteProdutoUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveCadastrarRestauranteProduto() {
        RestauranteProduto restauranteProduto = new RestauranteProduto();

        cadastrarRestauranteProdutoUseCase.execute(restauranteProduto);

        verify(restauranteProdutoGateway).create(restauranteProduto);
    }
}
