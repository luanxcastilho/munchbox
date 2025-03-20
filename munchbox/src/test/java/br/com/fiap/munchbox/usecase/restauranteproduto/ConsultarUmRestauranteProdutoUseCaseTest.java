package br.com.fiap.munchbox.usecase.restauranteproduto;

import br.com.fiap.munchbox.domain.core.RestauranteProduto;
import br.com.fiap.munchbox.domain.gateway.RestauranteProdutoGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class ConsultarUmRestauranteProdutoUseCaseTest {

    @Mock
    private RestauranteProdutoGateway restauranteProdutoGateway;

    @InjectMocks
    private ConsultarUmRestauranteProdutoUseCase consultarUmRestauranteProdutoUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveRetornarRestauranteProdutoQuandoEncontrado() {
        Long id = 1L;
        RestauranteProduto produtoEsperado = new RestauranteProduto();
        when(restauranteProdutoGateway.findById(id)).thenReturn(Optional.of(produtoEsperado));

        Optional<RestauranteProduto> resultado = consultarUmRestauranteProdutoUseCase.execute(id);

        assertTrue(resultado.isPresent());
        assertEquals(produtoEsperado, resultado.get());
        verify(restauranteProdutoGateway).findById(id);
    }

    @Test
    void deveRetornarVazioQuandoProdutoNaoEncontrado() {
        Long id = 1L;
        when(restauranteProdutoGateway.findById(id)).thenReturn(Optional.empty());

        Optional<RestauranteProduto> resultado = consultarUmRestauranteProdutoUseCase.execute(id);

        assertTrue(resultado.isEmpty());
        verify(restauranteProdutoGateway).findById(id);
    }
}
