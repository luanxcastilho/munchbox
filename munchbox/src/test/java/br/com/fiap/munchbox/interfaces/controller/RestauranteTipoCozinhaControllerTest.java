package br.com.fiap.munchbox.interfaces.controller;

import br.com.fiap.munchbox.domain.core.RestauranteProduto;
import br.com.fiap.munchbox.domain.core.RestauranteTipoCozinha;
import br.com.fiap.munchbox.interfaces.dto.RestauranteTipoCozinhaRequestDTO;
import br.com.fiap.munchbox.usecase.restaurantetipocozinha.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RestauranteTipoCozinhaControllerTest {

    @InjectMocks
    private RestauranteTipoCozinhaController restauranteTipoCozinhaController;

    @Mock
    private CadastrarRestauranteTipoCozinhaUseCase cadastrarRestauranteTipoCozinhaUseCase;

    @Mock
    private AtualizarRestauranteTipoCozinhaUseCase atualizarRestauranteTipoCozinhaUseCase;

    @Mock
    private RemoverRestauranteTipoCozinhaUseCase removerRestauranteTipoCozinhaUseCase;

    @Mock
    private ConsultarTodosRestauranteTipoCozinhaUseCase consultarTodosRestauranteTipoCozinhaUseCase;

    @Mock
    private ConsultarUmRestauranteTipoCozinhaUseCase consultarUmRestauranteTipoCozinhaUseCase;

    private RestauranteTipoCozinha restauranteTipoCozinha;

    @BeforeEach
    void setUp() {
        restauranteTipoCozinha = new RestauranteTipoCozinha();
        restauranteTipoCozinha.setId(1L);
        restauranteTipoCozinha.setNome("Italiana");
        restauranteTipoCozinha.setDataAtualizacao(LocalDateTime.now());
        restauranteTipoCozinha.setDataInclusao(LocalDateTime.now());
    }

    @Test
    void deveCadastrarRestauranteTipoCozinha() {
        RestauranteTipoCozinhaRequestDTO dto = new RestauranteTipoCozinhaRequestDTO("Italiana");
        ResponseEntity<RestauranteTipoCozinha> response = restauranteTipoCozinhaController.create(dto);

        assertEquals(201, response.getStatusCodeValue());
        verify(cadastrarRestauranteTipoCozinhaUseCase, times(1)).execute(any(RestauranteTipoCozinha.class));
    }

    @Test
    void deveAtualizarRestauranteTipoCozinha() {
        RestauranteTipoCozinhaRequestDTO dto = new RestauranteTipoCozinhaRequestDTO("Japonesa");
        when(consultarUmRestauranteTipoCozinhaUseCase.execute(1L)).thenReturn(Optional.of(restauranteTipoCozinha));

        ResponseEntity<RestauranteTipoCozinha> response = restauranteTipoCozinhaController.update(dto, 1L);

        assertEquals(200, response.getStatusCodeValue());
        verify(atualizarRestauranteTipoCozinhaUseCase, times(1)).execute(any(RestauranteTipoCozinha.class));
    }

    @Test
    void deveLancarExcecao_QuandoRestauranteTipoCozinhaNaoEncontrado_AoAtualizarRestauranteTipoCozinha() {
        RestauranteTipoCozinhaRequestDTO dto = new RestauranteTipoCozinhaRequestDTO("Japonesa");
        when(consultarUmRestauranteTipoCozinhaUseCase.execute(1L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            restauranteTipoCozinhaController.update(dto, 1L);
        });

        assertEquals("Tipo de cozinha de restaurante não encontrado", exception.getMessage());
        verify(consultarUmRestauranteTipoCozinhaUseCase, times(1)).execute(anyLong());
        verify(atualizarRestauranteTipoCozinhaUseCase, never()).execute(any(RestauranteTipoCozinha.class));
    }


    @Test
    void deveRemoverRestauranteTipoCozinha() {
        ResponseEntity<Void> response = restauranteTipoCozinhaController.delete(1L);

        assertEquals(200, response.getStatusCodeValue());

        verify(removerRestauranteTipoCozinhaUseCase, times(1)).execute(1L);
    }

    @Test
    void deveConsultarTodosRestauranteTipoCozinha() {
        when(consultarTodosRestauranteTipoCozinhaUseCase.execute(any(Pageable.class))).thenReturn(List.of(restauranteTipoCozinha));

        ResponseEntity<List<RestauranteTipoCozinha>> response = restauranteTipoCozinhaController.findAll(1, 15);

        assertEquals(200, response.getStatusCodeValue());
        assertFalse(response.getBody().isEmpty());
    }

    @Test
    void deveConsultarUmRestauranteTipoCozinha() {
        when(consultarUmRestauranteTipoCozinhaUseCase.execute(1L)).thenReturn(Optional.of(restauranteTipoCozinha));

        ResponseEntity<Optional<RestauranteTipoCozinha>> response = restauranteTipoCozinhaController.findById(1L);

        assertEquals(200, response.getStatusCodeValue());
        assertTrue(response.getBody().isPresent());
    }

    @Test
    void deveGravarLog_QuandoRestauranteTipoCozinhaNaoEncontrado_AoConsultarUmRestauranteTipoCozinha() {
        when(consultarUmRestauranteTipoCozinhaUseCase.execute(1L)).thenReturn(Optional.empty());

        ResponseEntity<Optional<RestauranteTipoCozinha>> response = restauranteTipoCozinhaController.findById(1L);

        assertEquals(200, response.getStatusCodeValue());
        assertFalse(response.getBody().isPresent());
    }
}
