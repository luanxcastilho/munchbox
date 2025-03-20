package br.com.fiap.munchbox.interfaces.controller;

import br.com.fiap.munchbox.domain.core.Restaurante;
import br.com.fiap.munchbox.domain.core.RestauranteFuncionamento;
import br.com.fiap.munchbox.interfaces.dto.RestauranteFuncionamentoRequestDTO;
import br.com.fiap.munchbox.usecase.restaurante.ConsultarUmRestauranteUseCase;
import br.com.fiap.munchbox.usecase.restaurantefuncionamento.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RestauranteFuncionamentoControllerTest {

    @InjectMocks
    private RestauranteFuncionamentoController restauranteFuncionamentoController;

    @Mock
    private CadastrarRestauranteFuncionamentoUseCase cadastrarRestauranteFuncionamentoUseCase;

    @Mock
    private AtualizarRestauranteFuncionamentoUseCase atualizarRestauranteFuncionamentoUseCase;

    @Mock
    private RemoverRestauranteFuncionamentoUseCase removerRestauranteFuncionamentoUseCase;

    @Mock
    private ConsultarTodosRestauranteFuncionamentoUseCase consultarTodosRestauranteFuncionamentoUseCase;

    @Mock
    private ConsultarUmRestauranteFuncionamentoUseCase consultarUmRestauranteFuncionamentoUseCase;

    @Mock
    private ConsultarUmRestauranteUseCase consultarUmRestauranteUseCase;

    private Restaurante restaurante;
    private RestauranteFuncionamento restauranteFuncionamento;

    @BeforeEach
    void setUp() {
        restaurante = new Restaurante();
        restaurante.setId(1L);

        restauranteFuncionamento = new RestauranteFuncionamento();
        restauranteFuncionamento.setId(1L);
        restauranteFuncionamento.setRestaurante(restaurante);
        restauranteFuncionamento.setDiaDaSemana(1);
        restauranteFuncionamento.setHorarioAbertura("08:00");
        restauranteFuncionamento.setHorarioFechamento("22:00");
        restauranteFuncionamento.setDataAtualizacao(LocalDateTime.now());
        restauranteFuncionamento.setDataInclusao(LocalDateTime.now());
    }

    @Test
    void deveCriarRestauranteFuncionamento() {
        RestauranteFuncionamentoRequestDTO dto = new RestauranteFuncionamentoRequestDTO(1L,
                                                                                               1,
                                                                                               "08:00",
                                                                                               "22:00");

        when(consultarUmRestauranteUseCase.execute(1L)).thenReturn(Optional.of(restaurante));

        ResponseEntity<RestauranteFuncionamento> response = restauranteFuncionamentoController.create(dto);

        assertEquals(201, response.getStatusCodeValue());
        verify(cadastrarRestauranteFuncionamentoUseCase, times(1)).execute(any(RestauranteFuncionamento.class));
    }

    @Test
    void deveLancarExcecao_QuandoRestauranteNaoEncontrado_AoCadastrarRestauranteFuncionamento() {
        RestauranteFuncionamentoRequestDTO dto = new RestauranteFuncionamentoRequestDTO(1L,
                                                                                               1,
                                                                                               "08:00",
                                                                                               "22:00");

        when(consultarUmRestauranteUseCase.execute(1L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            restauranteFuncionamentoController.create(dto);
        });

        assertEquals("Restaurante não encontrado", exception.getMessage());
        verify(consultarUmRestauranteUseCase, times(1)).execute(anyLong());
        verify(cadastrarRestauranteFuncionamentoUseCase, never()).execute(any(RestauranteFuncionamento.class));
    }
    

    @Test
    void deveAtualizarRestauranteFuncionamento() {
        RestauranteFuncionamentoRequestDTO dto = new RestauranteFuncionamentoRequestDTO(1L,
                                                                                               1,
                                                                                               "08:00",
                                                                                               "22:00");

        when(consultarUmRestauranteUseCase.execute(1L)).thenReturn(Optional.of(restaurante));
        when(consultarUmRestauranteFuncionamentoUseCase.execute(1L)).thenReturn(Optional.of(restauranteFuncionamento));

        ResponseEntity<RestauranteFuncionamento> response = restauranteFuncionamentoController.update(dto, 1L);

        assertEquals(200, response.getStatusCodeValue());
        verify(atualizarRestauranteFuncionamentoUseCase, times(1)).execute(any(RestauranteFuncionamento.class));
    }

    @Test
    void deveLancarExcecao_QuandoRestauranteNaoEncontrado_AoAtualizarRestauranteFuncionamento() {
        RestauranteFuncionamentoRequestDTO dto = new RestauranteFuncionamentoRequestDTO(1L,
                                                                                        1,
                                                                                        "08:00",
                                                                                        "22:00");

        when(consultarUmRestauranteUseCase.execute(1L)).thenReturn(Optional.empty());


        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            restauranteFuncionamentoController.update(dto, 1L);
        });

        assertEquals("Restaurante não encontrado", exception.getMessage());
        verify(consultarUmRestauranteUseCase, times(1)).execute(anyLong());
        verify(atualizarRestauranteFuncionamentoUseCase, never()).execute(any(RestauranteFuncionamento.class));
    }

    @Test
    void deveLancarExcecao_QuandoRestauranteFuncionamentoNaoEncontrado_AoAtualizarRestauranteFuncionamento() {
        RestauranteFuncionamentoRequestDTO dto = new RestauranteFuncionamentoRequestDTO(1L,
                                                                                        1,
                                                                                        "08:00",
                                                                                        "22:00");

        when(consultarUmRestauranteUseCase.execute(1L)).thenReturn(Optional.of(restaurante));
        when(consultarUmRestauranteFuncionamentoUseCase.execute(1L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            restauranteFuncionamentoController.update(dto, 1L);
        });

        assertEquals("Horário de funcionamento não encontrado", exception.getMessage());
        verify(consultarUmRestauranteFuncionamentoUseCase, times(1)).execute(anyLong());
        verify(atualizarRestauranteFuncionamentoUseCase, never()).execute(any(RestauranteFuncionamento.class));
    }

    @Test
    void deveRemoverRestauranteFuncionamento() {
        ResponseEntity<Void> response = restauranteFuncionamentoController.delete(1L);

        assertEquals(200, response.getStatusCodeValue());

        verify(removerRestauranteFuncionamentoUseCase, times(1)).execute(1L);
    }

    @Test
    void deveConsultarTodosRestaurantesFuncionamento() {
        when(consultarTodosRestauranteFuncionamentoUseCase.execute(any())).thenReturn(List.of(restauranteFuncionamento));

        ResponseEntity<List<RestauranteFuncionamento>> response = restauranteFuncionamentoController.findAll(1, 15);

        assertEquals(200, response.getStatusCodeValue());
        assertFalse(response.getBody().isEmpty());
    }

    @Test
    void deveConsultarUmRestauranteFuncionamento() {
        when(consultarUmRestauranteFuncionamentoUseCase.execute(1L)).thenReturn(Optional.of(restauranteFuncionamento));

        ResponseEntity<Optional<RestauranteFuncionamento>> response = restauranteFuncionamentoController.findById(1L);

        assertEquals(200, response.getStatusCodeValue());
        assertTrue(response.getBody().isPresent());
    }

    @Test
    void deveGravarLog_QuandoRestauranteFuncionamentoNaoEncontrado_AoConsultarUmRestauranteFuncionamento() {
        when(consultarUmRestauranteFuncionamentoUseCase.execute(1L)).thenReturn(Optional.empty());

        ResponseEntity<Optional<RestauranteFuncionamento>> response = restauranteFuncionamentoController.findById(1L);

        assertEquals(200, response.getStatusCodeValue());
        assertFalse(response.getBody().isPresent());
    }
}
