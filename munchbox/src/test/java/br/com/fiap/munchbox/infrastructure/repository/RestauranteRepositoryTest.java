package br.com.fiap.munchbox.infrastructure.repository;

import br.com.fiap.munchbox.domain.core.*;
import br.com.fiap.munchbox.infrastructure.entity.RestauranteEntity;
import br.com.fiap.munchbox.infrastructure.mapper.RestauranteMapper;
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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RestauranteRepositoryTest {

    @Mock
    private RestauranteRepositoryJpa restauranteRepositoryJpa;

    @InjectMocks
    private RestauranteRepository restauranteRepository;

    private Restaurante restaurante;
    private RestauranteEntity restauranteEntity;

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

        restaurante = new Restaurante(1L,
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

        restauranteEntity = RestauranteMapper.toEntity(restaurante);
    }

    @Test
    void deveCadastrarRestaurante() {
        restauranteRepository.create(restaurante);
        verify(restauranteRepositoryJpa, times(1)).save(any(RestauranteEntity.class));
    }

    @Test
    void deveAtualizarRestaurante() {
        restauranteRepository.update(restaurante);
        verify(restauranteRepositoryJpa, times(1)).save(any(RestauranteEntity.class));
    }

    @Test
    void deveExcluirRestaurante() {
        Long id = 1L;
        restauranteRepository.delete(id);
        verify(restauranteRepositoryJpa, times(1)).deleteById(id);
    }

    @Test
    void deveRetornarUmRestaurante() {
        when(restauranteRepositoryJpa.findById(1L)).thenReturn(Optional.of(restauranteEntity));
        Optional<Restaurante> result = restauranteRepository.findById(1L);
        assertTrue(result.isPresent());
        assertEquals(restaurante.getId(), result.get().getId());
    }

    @Test
    void deveRetornarTodosRestaurantes() {
        Pageable pageable = PageRequest.of(0, 10);
        List<RestauranteEntity> entities = List.of(restauranteEntity);
        Page<RestauranteEntity> page = new PageImpl<>(entities);

        when(restauranteRepositoryJpa.findAll(pageable)).thenReturn(page);

        List<Restaurante> result = restauranteRepository.findAll(pageable);
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
    }
}
