package br.com.fiap.munchbox.infrastructure.repository;

import br.com.fiap.munchbox.domain.core.RestauranteTipoCozinha;
import br.com.fiap.munchbox.infrastructure.entity.RestauranteTipoCozinhaEntity;
import br.com.fiap.munchbox.infrastructure.mapper.RestauranteTipoCozinhaMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RestauranteTipoCozinhaRepositoryTest {

    @Mock
    private RestauranteTipoCozinhaRepositoryJpa restauranteTipoCozinhaRepositoryJpa;

    @InjectMocks
    private RestauranteTipoCozinhaRepository restauranteTipoCozinhaRepository;

    private RestauranteTipoCozinha restauranteTipoCozinha;
    private RestauranteTipoCozinhaEntity restauranteTipoCozinhaEntity;

    @BeforeEach
    void setUp() {
        restauranteTipoCozinha = new RestauranteTipoCozinha(1L, "Italiana", LocalDateTime.now(), LocalDateTime.now());
        restauranteTipoCozinhaEntity = RestauranteTipoCozinhaMapper.toEntity(restauranteTipoCozinha);
    }

    @Test
    void deveCriar_QuandoRestauranteTipoCozinhaForValido_AoSalvar() {
        when(restauranteTipoCozinhaRepositoryJpa.save(any())).thenReturn(restauranteTipoCozinhaEntity);
        assertDoesNotThrow(() -> restauranteTipoCozinhaRepository.create(restauranteTipoCozinha));
        verify(restauranteTipoCozinhaRepositoryJpa, times(1)).save(any());
    }

    @Test
    void deveAtualizar_QuandoRestauranteTipoCozinhaForValido_AoSalvar() {
        when(restauranteTipoCozinhaRepositoryJpa.save(any())).thenReturn(restauranteTipoCozinhaEntity);
        assertDoesNotThrow(() -> restauranteTipoCozinhaRepository.update(restauranteTipoCozinha));
        verify(restauranteTipoCozinhaRepositoryJpa, times(1)).save(any());
    }

    @Test
    void deveDeletar_QuandoIdForValido_AoRemover() {
        doNothing().when(restauranteTipoCozinhaRepositoryJpa).deleteById(1L);
        assertDoesNotThrow(() -> restauranteTipoCozinhaRepository.delete(1L));
        verify(restauranteTipoCozinhaRepositoryJpa, times(1)).deleteById(1L);
    }

    @Test
    void deveRetornarRestauranteTipoCozinha_QuandoIdExistir_AoBuscarPorId() {
        when(restauranteTipoCozinhaRepositoryJpa.findById(1L)).thenReturn(Optional.of(restauranteTipoCozinhaEntity));
        Optional<RestauranteTipoCozinha> result = restauranteTipoCozinhaRepository.findById(1L);
        assertTrue(result.isPresent());
        assertEquals(restauranteTipoCozinha.getId(), result.get().getId());
    }

    @Test
    void deveRetornarLista_QuandoExistiremDados_AoBuscarTodos() {
        Page<RestauranteTipoCozinhaEntity> page = new PageImpl<>(List.of(restauranteTipoCozinhaEntity));
        when(restauranteTipoCozinhaRepositoryJpa.findAll(any(PageRequest.class))).thenReturn(page);
        List<RestauranteTipoCozinha> result = restauranteTipoCozinhaRepository.findAll(PageRequest.of(0, 10));
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
    }
}
