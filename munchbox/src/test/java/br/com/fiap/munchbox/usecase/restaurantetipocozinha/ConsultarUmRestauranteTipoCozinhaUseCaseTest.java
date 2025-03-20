package br.com.fiap.munchbox.usecase.restaurantetipocozinha;

import br.com.fiap.munchbox.domain.core.RestauranteTipoCozinha;
import br.com.fiap.munchbox.domain.gateway.RestauranteTipoCozinhaGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class ConsultarUmRestauranteTipoCozinhaUseCaseTest {

    @Mock
    private RestauranteTipoCozinhaGateway restauranteTipoCozinhaGateway;

    @InjectMocks
    private ConsultarUmRestauranteTipoCozinhaUseCase consultarUmRestauranteTipoCozinhaUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveConsultarUmRestauranteTipoCozinhaPorId() {
        Long id = 1L;
        RestauranteTipoCozinha restauranteTipoCozinha = new RestauranteTipoCozinha();
        when(restauranteTipoCozinhaGateway.findById(id)).thenReturn(Optional.of(restauranteTipoCozinha));

        Optional<RestauranteTipoCozinha> resultado = consultarUmRestauranteTipoCozinhaUseCase.execute(id);

        assertTrue(resultado.isPresent());
        assertEquals(restauranteTipoCozinha, resultado.get());
        verify(restauranteTipoCozinhaGateway).findById(id);
    }

    @Test
    void deveRetornarVazioQuandoNaoEncontrarRestauranteTipoCozinha() {
        Long id = 1L;
        when(restauranteTipoCozinhaGateway.findById(id)).thenReturn(Optional.empty());

        Optional<RestauranteTipoCozinha> resultado = consultarUmRestauranteTipoCozinhaUseCase.execute(id);

        assertTrue(resultado.isEmpty());
        verify(restauranteTipoCozinhaGateway).findById(id);
    }
}
