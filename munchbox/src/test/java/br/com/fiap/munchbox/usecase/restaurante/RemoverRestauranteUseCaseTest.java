package br.com.fiap.munchbox.usecase.restaurante;

import br.com.fiap.munchbox.domain.gateway.RestauranteGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

class RemoverRestauranteUseCaseTest {

    @Mock
    private RestauranteGateway restauranteGateway;

    @InjectMocks
    private RemoverRestauranteUseCase removerRestauranteUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveRemoverRestaurantePorId() {
        Long restauranteId = 1L;
        removerRestauranteUseCase.execute(restauranteId);

        verify(restauranteGateway).delete(restauranteId);
    }
}
