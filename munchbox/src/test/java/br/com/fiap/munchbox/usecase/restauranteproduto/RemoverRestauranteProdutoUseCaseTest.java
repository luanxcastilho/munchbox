package br.com.fiap.munchbox.usecase.restauranteproduto;

import br.com.fiap.munchbox.domain.gateway.RestauranteProdutoGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

class RemoverRestauranteProdutoUseCaseTest {

    @Mock
    private RestauranteProdutoGateway restauranteProdutoGateway;

    @InjectMocks
    private RemoverRestauranteProdutoUseCase removerRestauranteProdutoUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveRemoverRestauranteProduto() {
        Long id = 1L;

        removerRestauranteProdutoUseCase.execute(id);

        verify(restauranteProdutoGateway).delete(id);
    }
}
