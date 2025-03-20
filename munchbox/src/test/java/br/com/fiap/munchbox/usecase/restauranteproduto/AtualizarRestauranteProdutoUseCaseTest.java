package br.com.fiap.munchbox.usecase.restauranteproduto;

import br.com.fiap.munchbox.domain.core.RestauranteProduto;
import br.com.fiap.munchbox.domain.gateway.RestauranteProdutoGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

class AtualizarRestauranteProdutoUseCaseTest {

    @Mock
    private RestauranteProdutoGateway restauranteProdutoGateway;

    @InjectMocks
    private AtualizarRestauranteProdutoUseCase atualizarRestauranteProdutoUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveAtualizarRestauranteProduto() {
        RestauranteProduto restauranteProduto = new RestauranteProduto();

        atualizarRestauranteProdutoUseCase.execute(restauranteProduto);

        verify(restauranteProdutoGateway).update(restauranteProduto);
    }
}
