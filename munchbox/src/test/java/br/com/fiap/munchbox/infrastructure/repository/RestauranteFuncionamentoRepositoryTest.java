package br.com.fiap.munchbox.infrastructure.repository;

import br.com.fiap.munchbox.domain.core.*;
import br.com.fiap.munchbox.infrastructure.entity.RestauranteFuncionamentoEntity;
import br.com.fiap.munchbox.infrastructure.mapper.RestauranteFuncionamentoMapper;
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
class RestauranteFuncionamentoRepositoryTest {

    @Mock
    private RestauranteFuncionamentoRepositoryJpa restauranteFuncionamentoRepositoryJpa;

    @InjectMocks
    private RestauranteFuncionamentoRepository restauranteFuncionamentoRepository;

    private RestauranteFuncionamento restauranteFuncionamento;
    private RestauranteFuncionamentoEntity restauranteFuncionamentoEntity;

    @BeforeEach
    void setUp() {
        UsuarioPerfil usuarioPerfil = new UsuarioPerfil(1L, "Administrador", LocalDateTime.now(), LocalDateTime.now());

        Usuario usuario = new Usuario(1L,
                                      "usuario@test.com",
                                      "senha123",
                                      LocalDateTime.now(),
                                      LocalDateTime.now(),
                                      usuarioPerfil);

        RestauranteTipoCozinha tipoCozinha = new RestauranteTipoCozinha(1L,
                                                                        "Italiana",
                                                                        LocalDateTime.now(),
                                                                        LocalDateTime.now());
        Proprietario proprietario = new Proprietario(1L,
                                                     usuario,
                                                     "Proprietário Teste",
                                                     LocalDate.now(),
                                                     "11999999999",
                                                     "email@teste.com",
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

        restauranteFuncionamento = new RestauranteFuncionamento(1L,
                                                                restaurante,
                                                                1,
                                                                "08:00",
                                                                "18:00",
                                                                LocalDateTime.now(),
                                                                LocalDateTime.now());

        restauranteFuncionamentoEntity = RestauranteFuncionamentoMapper.toEntity(restauranteFuncionamento);
    }

    @Test
    void deveCadastrarRestauranteFuncionamento() {
        restauranteFuncionamentoRepository.create(restauranteFuncionamento);
        verify(restauranteFuncionamentoRepositoryJpa, times(1)).save(any(RestauranteFuncionamentoEntity.class));
    }

    @Test
    void deveAtualizarRestauranteFuncionamento() {
        restauranteFuncionamentoRepository.update(restauranteFuncionamento);
        verify(restauranteFuncionamentoRepositoryJpa, times(1)).save(any(RestauranteFuncionamentoEntity.class));
    }

    @Test
    void deveExcluirRestauranteFuncionamento() {
        Long id = 1L;
        restauranteFuncionamentoRepository.delete(id);
        verify(restauranteFuncionamentoRepositoryJpa, times(1)).deleteById(id);
    }

    @Test
    void deveRetornarUmRestauranteFuncionamento() {
        when(restauranteFuncionamentoRepositoryJpa.findById(1L)).thenReturn(Optional.of(restauranteFuncionamentoEntity));
        Optional<RestauranteFuncionamento> result = restauranteFuncionamentoRepository.findById(1L);
        assertTrue(result.isPresent());
        assertEquals(restauranteFuncionamento.getId(), result.get().getId());
    }

    @Test
    void deveRetornarTodosRestauranteFuncionamento() {
        Pageable pageable = PageRequest.of(0, 10);
        List<RestauranteFuncionamentoEntity> entities = List.of(restauranteFuncionamentoEntity);
        Page<RestauranteFuncionamentoEntity> page = new PageImpl<>(entities);

        when(restauranteFuncionamentoRepositoryJpa.findAll(pageable)).thenReturn(page);

        List<RestauranteFuncionamento> result = restauranteFuncionamentoRepository.findAll(pageable);
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
    }
}
