package br.com.fiap.munchbox.usecase.restauranteproduto;

import br.com.fiap.munchbox.domain.core.RestauranteProduto;
import br.com.fiap.munchbox.domain.gateway.RestauranteProdutoGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ConsultarTodosRestauranteProdutoUseCaseTest {

    @Mock
    private RestauranteProdutoGateway restauranteProdutoGateway;

    @InjectMocks
    private ConsultarTodosRestauranteProdutoUseCase consultarTodosRestauranteProdutoUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveRetornarListaDeProdutos() {
        Pageable pageable = mock(Pageable.class);
        List<RestauranteProduto> produtosEsperados = List.of(new RestauranteProduto(), new RestauranteProduto());

        when(restauranteProdutoGateway.findAll(pageable)).thenReturn(produtosEsperados);

        List<RestauranteProduto> resultado = consultarTodosRestauranteProdutoUseCase.execute(pageable);

        assertEquals(produtosEsperados, resultado);
        verify(restauranteProdutoGateway).findAll(pageable);
    }
}
