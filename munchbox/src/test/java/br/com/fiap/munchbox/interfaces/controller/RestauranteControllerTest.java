package br.com.fiap.munchbox.interfaces.controller;

import br.com.fiap.munchbox.domain.core.Cliente;
import br.com.fiap.munchbox.domain.core.Proprietario;
import br.com.fiap.munchbox.domain.core.Restaurante;
import br.com.fiap.munchbox.domain.core.RestauranteTipoCozinha;
import br.com.fiap.munchbox.interfaces.dto.ClienteRequestDTO;
import br.com.fiap.munchbox.interfaces.dto.RestauranteRequestDTO;
import br.com.fiap.munchbox.usecase.proprietario.ConsultarUmProprietarioUseCase;
import br.com.fiap.munchbox.usecase.restaurante.*;
import br.com.fiap.munchbox.usecase.restaurantetipocozinha.ConsultarUmRestauranteTipoCozinhaUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RestauranteControllerTest {

    @InjectMocks
    private RestauranteController restauranteController;

    @Mock
    private CadastrarRestauranteUseCase cadastrarRestauranteUseCase;
    @Mock
    private AtualizarRestauranteUseCase atualizarRestauranteUseCase;
    @Mock
    private RemoverRestauranteUseCase removerRestauranteUseCase;
    @Mock
    private ConsultarTodosRestauranteUseCase consultarTodosRestauranteUseCase;
    @Mock
    private ConsultarUmRestauranteUseCase consultarUmRestauranteUseCase;
    @Mock
    private ConsultarUmRestauranteTipoCozinhaUseCase consultarUmRestauranteTipoCozinhaUseCase;
    @Mock
    private ConsultarUmProprietarioUseCase consultarUmProprietarioUseCase;

    private Restaurante restaurante;
    private RestauranteTipoCozinha restauranteTipoCozinha;
    private Proprietario proprietario;

    @BeforeEach
    void setUp() {
        restauranteTipoCozinha = new RestauranteTipoCozinha();
        restauranteTipoCozinha.setId(1L);
        restauranteTipoCozinha.setNome("Italiana");

        proprietario = new Proprietario();
        proprietario.setId(1L);
        proprietario.setNome("Proprietário Teste");

        restaurante = new Restaurante();
        restaurante.setId(1L);
        restaurante.setNome("Restaurante Teste");
        restaurante.setRestauranteTipoCozinha(restauranteTipoCozinha);
        restaurante.setProprietario(proprietario);
        restaurante.setDataInclusao(LocalDateTime.now());
        restaurante.setDataAtualizacao(LocalDateTime.now());
    }

    @Test
    void deveCadastrarRestaurante() {
        RestauranteRequestDTO dto = new RestauranteRequestDTO("Restaurante Teste",
                                                              1L,
                                                              1L,
                                                              "Rua Teste",
                                                              "100",
                                                              "Apto 10",
                                                              "Bairro Teste",
                                                              "Cidade Teste",
                                                              "Estado Teste",
                                                              "00000-000");

        when(consultarUmRestauranteTipoCozinhaUseCase.execute(anyLong())).thenReturn(Optional.of(restauranteTipoCozinha));
        when(consultarUmProprietarioUseCase.execute(anyLong())).thenReturn(Optional.of(proprietario));
        doNothing().when(cadastrarRestauranteUseCase).execute(any(Restaurante.class));

        ResponseEntity<Restaurante> response = restauranteController.create(dto);

        assertEquals(201, response.getStatusCodeValue());
        verify(cadastrarRestauranteUseCase, times(1)).execute(any(Restaurante.class));
    }

    @Test
    void deveLancarExcecao_QuandoTipoCozinhaNaoEncontrado_AoCadastrarRestaurante() {

        RestauranteRequestDTO dto = new RestauranteRequestDTO("Restaurante Teste",
                                                              1L,
                                                              1L,
                                                              "Rua Teste",
                                                              "100",
                                                              "Apto 10",
                                                              "Bairro Teste",
                                                              "Cidade Teste",
                                                              "Estado Teste",
                                                              "00000-000");

        when(consultarUmRestauranteTipoCozinhaUseCase.execute(anyLong())).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            restauranteController.create(dto);
        });

        assertEquals("Tipo de cozinha não encontrado", exception.getMessage());
        verify(consultarUmRestauranteTipoCozinhaUseCase, times(1)).execute(anyLong());
        verify(cadastrarRestauranteUseCase, never()).execute(any(Restaurante.class));
    }

    @Test
    void deveLancarExcecao_QuandoProprietarioNaoEncontrado_AoCadastrarRestaurante() {

        RestauranteRequestDTO dto = new RestauranteRequestDTO("Restaurante Teste",
                                                              1L,
                                                              1L,
                                                              "Rua Teste",
                                                              "100",
                                                              "Apto 10",
                                                              "Bairro Teste",
                                                              "Cidade Teste",
                                                              "Estado Teste",
                                                              "00000-000");

        when(consultarUmRestauranteTipoCozinhaUseCase.execute(anyLong())).thenReturn(Optional.of(restauranteTipoCozinha));
        when(consultarUmProprietarioUseCase.execute(anyLong())).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            restauranteController.create(dto);
        });

        assertEquals("Proprietário não encontrado", exception.getMessage());
        verify(consultarUmProprietarioUseCase, times(1)).execute(anyLong());
        verify(cadastrarRestauranteUseCase, never()).execute(any(Restaurante.class));
    }

    @Test
    void deveAtualizarRestaurante() {
        RestauranteRequestDTO dto = new RestauranteRequestDTO("Restaurante Atualizado",
                                                              1L,
                                                              1L,
                                                              "Rua Atualizada",
                                                              "200",
                                                              "Apto 20",
                                                              "Bairro Atualizado",
                                                              "Cidade Atualizada",
                                                              "Estado Atualizado",
                                                              "11111-111");

        when(consultarUmRestauranteUseCase.execute(anyLong())).thenReturn(Optional.of(restaurante));
        when(consultarUmRestauranteTipoCozinhaUseCase.execute(anyLong())).thenReturn(Optional.of(restauranteTipoCozinha));
        when(consultarUmProprietarioUseCase.execute(anyLong())).thenReturn(Optional.of(proprietario));
        doNothing().when(atualizarRestauranteUseCase).execute(any(Restaurante.class));

        ResponseEntity<Restaurante> response = restauranteController.update(dto, 1L);

        assertEquals(200, response.getStatusCodeValue());
        verify(atualizarRestauranteUseCase, times(1)).execute(any(Restaurante.class));
    }

    @Test
    void deveLancarExcecao_QuandoRestauranteNaoEncontrado_AoAtualizarRestaurante() {

        RestauranteRequestDTO dto = new RestauranteRequestDTO("Restaurante Teste",
                                                              1L,
                                                              1L,
                                                              "Rua Teste",
                                                              "100",
                                                              "Apto 10",
                                                              "Bairro Teste",
                                                              "Cidade Teste",
                                                              "Estado Teste",
                                                              "00000-000");

        when(consultarUmRestauranteUseCase.execute(anyLong())).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            restauranteController.update(dto, 1L);
        });

        assertEquals("Restaurante não encontrado", exception.getMessage());
        verify(consultarUmRestauranteUseCase, times(1)).execute(anyLong());
        verify(atualizarRestauranteUseCase, never()).execute(any(Restaurante.class));
    }

    @Test
    void deveLancarExcecao_QuandoTipoCozinhaNaoEncontrado_AoAtualizarRestaurante() {

        RestauranteRequestDTO dto = new RestauranteRequestDTO("Restaurante Teste",
                                                              1L,
                                                              1L,
                                                              "Rua Teste",
                                                              "100",
                                                              "Apto 10",
                                                              "Bairro Teste",
                                                              "Cidade Teste",
                                                              "Estado Teste",
                                                              "00000-000");

        when(consultarUmRestauranteUseCase.execute(anyLong())).thenReturn(Optional.of(restaurante));
        when(consultarUmRestauranteTipoCozinhaUseCase.execute(anyLong())).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            restauranteController.update(dto, 1L);
        });

        assertEquals("Tipo de cozinha não encontrado", exception.getMessage());
        verify(consultarUmRestauranteTipoCozinhaUseCase, times(1)).execute(anyLong());
        verify(atualizarRestauranteUseCase, never()).execute(any(Restaurante.class));
    }

    @Test
    void deveLancarExcecao_QuandoProprietarioNaoEncontrado_AoAtualizarRestaurante() {

        RestauranteRequestDTO dto = new RestauranteRequestDTO("Restaurante Teste",
                                                              1L,
                                                              1L,
                                                              "Rua Teste",
                                                              "100",
                                                              "Apto 10",
                                                              "Bairro Teste",
                                                              "Cidade Teste",
                                                              "Estado Teste",
                                                              "00000-000");

        when(consultarUmRestauranteUseCase.execute(anyLong())).thenReturn(Optional.of(restaurante));
        when(consultarUmRestauranteTipoCozinhaUseCase.execute(anyLong())).thenReturn(Optional.of(restauranteTipoCozinha));
        when(consultarUmProprietarioUseCase.execute(anyLong())).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            restauranteController.update(dto, 1L);
        });

        assertEquals("Proprietário não encontrado", exception.getMessage());
        verify(consultarUmProprietarioUseCase, times(1)).execute(anyLong());
        verify(atualizarRestauranteUseCase, never()).execute(any(Restaurante.class));
    }

    @Test
    void deveConsultarUmRestaurante() {
        when(consultarUmRestauranteUseCase.execute(anyLong())).thenReturn(Optional.of(restaurante));

        ResponseEntity<Optional<Restaurante>> response = restauranteController.findById(1L);

        assertEquals(200, response.getStatusCodeValue());
        assertTrue(response.getBody().isPresent());
        assertEquals("Restaurante Teste", response.getBody().get().getNome());
    }

    @Test
    void deveGravarLog_QuandoRestauranteNaoEncontrado_AoConsultarUmRestaurante() {

        when(consultarUmRestauranteUseCase.execute(anyLong())).thenReturn(Optional.empty());

        ResponseEntity<Optional<Restaurante>> response = restauranteController.findById(1L);

        assertEquals(200, response.getStatusCodeValue());
        assertFalse(response.getBody().isPresent());
    }

    @Test
    void deveConsultarTodosRestaurantes() {
        when(consultarTodosRestauranteUseCase.execute(any())).thenReturn(List.of(restaurante));

        ResponseEntity<List<Restaurante>> response = restauranteController.findAll(1, 10);

        assertEquals(200, response.getStatusCodeValue());
        assertFalse(response.getBody().isEmpty());
    }

    @Test
    void deveRemoverRestaurante() {
        doNothing().when(removerRestauranteUseCase).execute(anyLong());

        ResponseEntity<Void> response = restauranteController.delete(1L);

        assertEquals(200, response.getStatusCodeValue());
        verify(removerRestauranteUseCase, times(1)).execute(anyLong());
    }
}
