package br.com.fiap.munchbox.usecase.restaurantetipocozinha;

import br.com.fiap.munchbox.domain.core.RestauranteTipoCozinha;
import br.com.fiap.munchbox.domain.gateway.RestauranteTipoCozinhaGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

class CadastrarRestauranteTipoCozinhaUseCaseTest {

    @Mock
    private RestauranteTipoCozinhaGateway restauranteTipoCozinhaGateway;

    @InjectMocks
    private CadastrarRestauranteTipoCozinhaUseCase cadastrarRestauranteTipoCozinhaUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveCadastrarRestauranteTipoCozinha() {
        RestauranteTipoCozinha restauranteTipoCozinha = new RestauranteTipoCozinha();

        cadastrarRestauranteTipoCozinhaUseCase.execute(restauranteTipoCozinha);

        verify(restauranteTipoCozinhaGateway).create(restauranteTipoCozinha);
    }
}
