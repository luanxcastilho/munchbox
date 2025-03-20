package br.com.fiap.munchbox.usecase.restaurantefuncionamento;

import br.com.fiap.munchbox.domain.core.RestauranteFuncionamento;
import br.com.fiap.munchbox.domain.gateway.RestauranteFuncionamentoGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ConsultarUmRestauranteFuncionamentoUseCaseTest {

    @Mock
    private RestauranteFuncionamentoGateway restauranteFuncionamentoGateway;

    @InjectMocks
    private ConsultarUmRestauranteFuncionamentoUseCase consultarUmRestauranteFuncionamentoUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveConsultarUmRestauranteFuncionamento() {
        Long id = 1L;
        RestauranteFuncionamento restauranteFuncionamento = new RestauranteFuncionamento();
        when(restauranteFuncionamentoGateway.findById(id)).thenReturn(Optional.of(restauranteFuncionamento));

        Optional<RestauranteFuncionamento> resultado = consultarUmRestauranteFuncionamentoUseCase.execute(id);

        assertTrue(resultado.isPresent());
        assertEquals(restauranteFuncionamento, resultado.get());
        verify(restauranteFuncionamentoGateway).findById(id);
    }

    @Test
    void deveRetornarVazioQuandoRestauranteFuncionamentoNaoExistir() {
        Long id = 1L;
        when(restauranteFuncionamentoGateway.findById(id)).thenReturn(Optional.empty());

        Optional<RestauranteFuncionamento> resultado = consultarUmRestauranteFuncionamentoUseCase.execute(id);

        assertFalse(resultado.isPresent());
        verify(restauranteFuncionamentoGateway).findById(id);
    }
}
