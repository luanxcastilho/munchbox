package br.com.fiap.munchbox.usecase.restaurantefuncionamento;

import br.com.fiap.munchbox.domain.core.RestauranteFuncionamento;
import br.com.fiap.munchbox.domain.gateway.RestauranteFuncionamentoGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class ConsultarTodosRestauranteFuncionamentoUseCaseTest {

    @Mock
    private RestauranteFuncionamentoGateway restauranteFuncionamentoGateway;

    @Mock
    private Pageable pageable;

    @InjectMocks
    private ConsultarTodosRestauranteFuncionamentoUseCase consultarTodosRestauranteFuncionamentoUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveConsultarTodosRestaurantesFuncionamento() {
        List<RestauranteFuncionamento> listaMock = List.of(new RestauranteFuncionamento(), new RestauranteFuncionamento());
        when(restauranteFuncionamentoGateway.findAll(pageable)).thenReturn(listaMock);

        List<RestauranteFuncionamento> resultado = consultarTodosRestauranteFuncionamentoUseCase.execute(pageable);

        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        verify(restauranteFuncionamentoGateway).findAll(pageable);
    }
}
