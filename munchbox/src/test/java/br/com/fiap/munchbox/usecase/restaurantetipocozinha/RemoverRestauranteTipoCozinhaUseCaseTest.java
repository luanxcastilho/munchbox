package br.com.fiap.munchbox.usecase.restaurantetipocozinha;

import br.com.fiap.munchbox.domain.gateway.RestauranteTipoCozinhaGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

class RemoverRestauranteTipoCozinhaUseCaseTest {

    @Mock
    private RestauranteTipoCozinhaGateway restauranteTipoCozinhaGateway;

    @InjectMocks
    private RemoverRestauranteTipoCozinhaUseCase removerRestauranteTipoCozinhaUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveRemoverRestauranteTipoCozinhaPorId() {
        Long id = 1L;

        removerRestauranteTipoCozinhaUseCase.execute(id);

        verify(restauranteTipoCozinhaGateway).delete(id);
    }
}
