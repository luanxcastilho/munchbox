package br.com.fiap.munchbox.infrastructure.repository;

import br.com.fiap.munchbox.domain.core.*;
import br.com.fiap.munchbox.infrastructure.entity.RestauranteProdutoEntity;
import br.com.fiap.munchbox.infrastructure.mapper.RestauranteProdutoMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RestauranteProdutoRepositoryTest {

    @Mock
    private RestauranteProdutoRepositoryJpa restauranteProdutoRepositoryJpa;

    @InjectMocks
    private RestauranteProdutoRepository restauranteProdutoRepository;

    private RestauranteProduto restauranteProduto;
    private RestauranteProdutoEntity restauranteProdutoEntity;

    @BeforeEach
    void setUp() {
        UsuarioPerfil usuarioPerfil = new UsuarioPerfil(1L, "Administrador", LocalDateTime.now(), LocalDateTime.now());

        Usuario usuario = new Usuario(1L,
                                      "usuario@test.com",
                                      "senha123",
                                      LocalDateTime.now(),
                                      LocalDateTime.now(),
                                      usuarioPerfil);

        Proprietario proprietario = new Proprietario(1L,
                                                     usuario,
                                                     "Nome Proprietario",
                                                     LocalDate.of(1985, 5, 20),
                                                     "11999999999",
                                                     "proprietario@test.com",
                                                     LocalDateTime.now(),
                                                     LocalDateTime.now());

        RestauranteTipoCozinha tipoCozinha = new RestauranteTipoCozinha(1L,
                                                                        "Italiana",
                                                                        LocalDateTime.now(),
                                                                        LocalDateTime.now());

        Restaurante restaurante = new Restaurante(1L,
                                                  tipoCozinha,
                                                  proprietario,
                                                  "Restaurante Teste",
                                                  "Rua Teste",
                                                  "123",
                                                  "Complemento",
                                                  "Bairro",
                                                  "Cidade",
                                                  "Estado",
                                                  "12345-678",
                                                  LocalDateTime.now(),
                                                  LocalDateTime.now());

        restauranteProduto = new RestauranteProduto(1L,
                                                    restaurante,
                                                    "Produto Teste",
                                                    "Descrição Teste",
                                                    new BigDecimal("29.90"),
                                                    "imagem.jpg",
                                                    "S",
                                                    LocalDateTime.now(),
                                                    LocalDateTime.now());

        restauranteProdutoEntity = RestauranteProdutoMapper.toEntity(restauranteProduto);
    }

    @Test
    void deveCadastrarRestauranteProduto() {
        restauranteProdutoRepository.create(restauranteProduto);
        verify(restauranteProdutoRepositoryJpa, times(1)).save(any(RestauranteProdutoEntity.class));
    }

    @Test
    void deveAtualizarRestauranteProduto() {
        restauranteProdutoRepository.update(restauranteProduto);
        verify(restauranteProdutoRepositoryJpa, times(1)).save(any(RestauranteProdutoEntity.class));
    }

    @Test
    void deveExcluirRestauranteProduto() {
        Long id = 1L;
        restauranteProdutoRepository.delete(id);
        verify(restauranteProdutoRepositoryJpa, times(1)).deleteById(id);
    }

    @Test
    void deveRetornarUmRestauranteProduto() {
        when(restauranteProdutoRepositoryJpa.findById(1L)).thenReturn(Optional.of(restauranteProdutoEntity));
        Optional<RestauranteProduto> result = restauranteProdutoRepository.findById(1L);
        assertTrue(result.isPresent());
        assertEquals(restauranteProduto.getId(), result.get().getId());
    }

    @Test
    void deveRetornarTodosRestauranteProduto() {
        Pageable pageable = PageRequest.of(0, 10);
        List<RestauranteProdutoEntity> entities = List.of(restauranteProdutoEntity);
        Page<RestauranteProdutoEntity> page = new PageImpl<>(entities);

        when(restauranteProdutoRepositoryJpa.findAll(pageable)).thenReturn(page);

        List<RestauranteProduto> result = restauranteProdutoRepository.findAll(pageable);
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
    }
}
