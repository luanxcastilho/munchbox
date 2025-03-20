package br.com.fiap.munchbox.interfaces.controller;

import br.com.fiap.munchbox.domain.core.Restaurante;
import br.com.fiap.munchbox.domain.core.RestauranteFuncionamento;
import br.com.fiap.munchbox.domain.core.RestauranteProduto;
import br.com.fiap.munchbox.interfaces.dto.RestauranteFuncionamentoRequestDTO;
import br.com.fiap.munchbox.interfaces.dto.RestauranteProdutoRequestDTO;
import br.com.fiap.munchbox.usecase.restaurante.ConsultarUmRestauranteUseCase;
import br.com.fiap.munchbox.usecase.restauranteproduto.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RestauranteProdutoControllerTest {

    @InjectMocks
    private RestauranteProdutoController restauranteProdutoController;

    @Mock
    private CadastrarRestauranteProdutoUseCase cadastrarRestauranteProdutoUseCase;

    @Mock
    private AtualizarRestauranteProdutoUseCase atualizarRestauranteProdutoUseCase;

    @Mock
    private RemoverRestauranteProdutoUseCase removerRestauranteProdutoUseCase;

    @Mock
    private ConsultarTodosRestauranteProdutoUseCase consultarTodosRestauranteProdutoUseCase;

    @Mock
    private ConsultarUmRestauranteProdutoUseCase consultarUmRestauranteProdutoUseCase;

    @Mock
    private ConsultarUmRestauranteUseCase consultarUmRestauranteUseCase;

    private Restaurante restaurante;
    private RestauranteProduto restauranteProduto;

    @BeforeEach
    void setUp() {
        restaurante = new Restaurante();
        restaurante.setId(1L);

        restauranteProduto = new RestauranteProduto();
        restauranteProduto.setId(1L);
        restauranteProduto.setRestaurante(restaurante);
        restauranteProduto.setNome("Produto Teste");
        restauranteProduto.setDescricao("Descrição Teste");
        restauranteProduto.setValor(BigDecimal.valueOf(10.0));
        restauranteProduto.setImagem("imagem.png");
        restauranteProduto.setPermiteEntrega("S");
        restauranteProduto.setDataAtualizacao(LocalDateTime.now());
        restauranteProduto.setDataInclusao(LocalDateTime.now());
    }

    @Test
    void deveCadastrarRestauranteProduto() {
        RestauranteProdutoRequestDTO dto = new RestauranteProdutoRequestDTO(1L,
                                                                            "Produto Teste",
                                                                            "Descrição Teste",
                                                                            BigDecimal.valueOf(10.0),
                                                                            "imagem.png",
                                                                            "S");

        when(consultarUmRestauranteUseCase.execute(1L)).thenReturn(Optional.of(restaurante));
        doNothing().when(cadastrarRestauranteProdutoUseCase).execute(any(RestauranteProduto.class));

        ResponseEntity<RestauranteProduto> response = restauranteProdutoController.create(dto);

        assertEquals(201, response.getStatusCodeValue());
        verify(cadastrarRestauranteProdutoUseCase, times(1)).execute(any(RestauranteProduto.class));
    }

    @Test
    void deveLancarExcecao_QuandoRestauranteNaoEncontrado_AoCadastrarRestauranteProduto() {
        RestauranteProdutoRequestDTO dto = new RestauranteProdutoRequestDTO(1L,
                                                                            "Produto Teste",
                                                                            "Descrição Teste",
                                                                            BigDecimal.valueOf(10.0),
                                                                            "imagem.png",
                                                                            "S");

        when(consultarUmRestauranteUseCase.execute(1L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            restauranteProdutoController.create(dto);
        });

        assertEquals("Restaurante não encontrado", exception.getMessage());
        verify(consultarUmRestauranteUseCase, times(1)).execute(anyLong());
        verify(cadastrarRestauranteProdutoUseCase, never()).execute(any(RestauranteProduto.class));
    }

    @Test
    void deveAtualizarRestauranteProduto() {
        RestauranteProdutoRequestDTO dto = new RestauranteProdutoRequestDTO(1L,
                                                                            "Produto Teste",
                                                                            "Descrição Teste",
                                                                            BigDecimal.valueOf(10.0),
                                                                            "imagem.png",
                                                                            "S");

        when(consultarUmRestauranteProdutoUseCase.execute(1L)).thenReturn(Optional.of(restauranteProduto));
        when(consultarUmRestauranteUseCase.execute(1L)).thenReturn(Optional.of(restaurante));
        doNothing().when(atualizarRestauranteProdutoUseCase).execute(any(RestauranteProduto.class));

        ResponseEntity<RestauranteProduto> response = restauranteProdutoController.update(dto, 1L);

        assertEquals(200, response.getStatusCodeValue());
        verify(atualizarRestauranteProdutoUseCase, times(1)).execute(any(RestauranteProduto.class));
    }

    @Test
    void deveLancarExcecao_QuandoRestauranteNaoEncontrado_AoAtualizarRestauranteProduto() {

        RestauranteProdutoRequestDTO dto = new RestauranteProdutoRequestDTO(1L,
                                                                            "Produto Teste",
                                                                            "Descrição Teste",
                                                                            BigDecimal.valueOf(10.0),
                                                                            "imagem.png",
                                                                            "S");

        when(consultarUmRestauranteProdutoUseCase.execute(1L)).thenReturn(Optional.of(restauranteProduto));
        when(consultarUmRestauranteUseCase.execute(1L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            restauranteProdutoController.update(dto, 1L);
        });

        assertEquals("Restaurante não encontrado", exception.getMessage());
        verify(consultarUmRestauranteUseCase, times(1)).execute(anyLong());
        verify(cadastrarRestauranteProdutoUseCase, never()).execute(any(RestauranteProduto.class));
    }

    @Test
    void deveLancarExcecao_QuandoRestauranteProdutoNaoEncontrado_AoAtualizarRestauranteProduto() {

        RestauranteProdutoRequestDTO dto = new RestauranteProdutoRequestDTO(1L,
                                                                            "Produto Teste",
                                                                            "Descrição Teste",
                                                                            BigDecimal.valueOf(10.0),
                                                                            "imagem.png",
                                                                            "S");

        when(consultarUmRestauranteProdutoUseCase.execute(1L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            restauranteProdutoController.update(dto, 1L);
        });

        assertEquals("Produto não encontrado", exception.getMessage());
        verify(consultarUmRestauranteProdutoUseCase, times(1)).execute(anyLong());
        verify(cadastrarRestauranteProdutoUseCase, never()).execute(any(RestauranteProduto.class));
    }

    @Test
    void deveRemoverRestauranteProduto() {
        ResponseEntity<Void> response = restauranteProdutoController.delete(1L);

        assertEquals(200, response.getStatusCodeValue());

        verify(removerRestauranteProdutoUseCase, times(1)).execute(1L);
    }

    @Test
    void deveConsultarTodosRestaurantesProdutos() {
        when(consultarTodosRestauranteProdutoUseCase.execute(any(Pageable.class))).thenReturn(List.of(restauranteProduto));

        ResponseEntity<List<RestauranteProduto>> response = restauranteProdutoController.findAll(1, 15);

        assertEquals(200, response.getStatusCodeValue());
        assertFalse(response.getBody().isEmpty());
    }

    @Test
    void deveConsultarUmRestauranteProduto() {
        when(consultarUmRestauranteProdutoUseCase.execute(1L)).thenReturn(Optional.of(restauranteProduto));

        ResponseEntity<Optional<RestauranteProduto>> response = restauranteProdutoController.findById(1L);

        assertEquals(200, response.getStatusCodeValue());
        assertTrue(response.getBody().isPresent());
    }

    @Test
    void deveGravarLog_QuandoRestauranteProdutoNaoExistir_AoConsultarUmRestauranteProduto() {
        when(consultarUmRestauranteProdutoUseCase.execute(1L)).thenReturn(Optional.empty());

        ResponseEntity<Optional<RestauranteProduto>> response = restauranteProdutoController.findById(1L);

        assertEquals(200, response.getStatusCodeValue());
        assertFalse(response.getBody().isPresent());
    }
}
