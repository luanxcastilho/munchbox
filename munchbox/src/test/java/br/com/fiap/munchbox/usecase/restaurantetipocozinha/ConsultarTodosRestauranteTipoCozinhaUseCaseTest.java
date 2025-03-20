package br.com.fiap.munchbox.usecase.restaurantetipocozinha;

import br.com.fiap.munchbox.domain.core.RestauranteTipoCozinha;
import br.com.fiap.munchbox.domain.gateway.RestauranteTipoCozinhaGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ConsultarTodosRestauranteTipoCozinhaUseCaseTest {

    @Mock
    private RestauranteTipoCozinhaGateway restauranteTipoCozinhaGateway;

    @Mock
    private Pageable pageable;

    @InjectMocks
    private ConsultarTodosRestauranteTipoCozinhaUseCase consultarTodosRestauranteTipoCozinhaUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveConsultarTodosRestauranteTipoCozinha() {
        List<RestauranteTipoCozinha> listaEsperada = List.of(new RestauranteTipoCozinha(), new RestauranteTipoCozinha());
        when(restauranteTipoCozinhaGateway.findAll(pageable)).thenReturn(listaEsperada);

        List<RestauranteTipoCozinha> resultado = consultarTodosRestauranteTipoCozinhaUseCase.execute(pageable);

        assertEquals(listaEsperada, resultado);
        verify(restauranteTipoCozinhaGateway).findAll(pageable);
    }
}
