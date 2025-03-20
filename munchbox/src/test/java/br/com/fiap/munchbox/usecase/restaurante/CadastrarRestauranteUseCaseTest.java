package br.com.fiap.munchbox.usecase.restaurante;

import br.com.fiap.munchbox.domain.core.Restaurante;
import br.com.fiap.munchbox.domain.gateway.RestauranteGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

class CadastrarRestauranteUseCaseTest {

    @Mock
    private RestauranteGateway restauranteGateway;

    @InjectMocks
    private CadastrarRestauranteUseCase cadastrarRestauranteUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveCadastrarRestaurante() {
        Restaurante restaurante = new Restaurante();

        cadastrarRestauranteUseCase.execute(restaurante);

        verify(restauranteGateway).create(restaurante);
    }
}