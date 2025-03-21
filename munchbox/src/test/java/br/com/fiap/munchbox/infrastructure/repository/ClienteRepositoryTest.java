package br.com.fiap.munchbox.infrastructure.repository;

import br.com.fiap.munchbox.domain.core.Cliente;
import br.com.fiap.munchbox.domain.core.Usuario;
import br.com.fiap.munchbox.domain.core.UsuarioPerfil;
import br.com.fiap.munchbox.infrastructure.entity.ClienteEntity;
import br.com.fiap.munchbox.infrastructure.mapper.ClienteMapper;
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
class ClienteRepositoryTest {

    @Mock
    private ClienteRepositoryJpa clienteRepositoryJpa;

    @InjectMocks
    private ClienteRepository clienteRepository;

    private Cliente cliente;
    private ClienteEntity clienteEntity;

    @BeforeEach
    void setUp() {
        UsuarioPerfil usuarioPerfil = new UsuarioPerfil(1L, "Administrador", LocalDateTime.now(), LocalDateTime.now());

        Usuario usuario = new Usuario(1L,
                                      "usuario@test.com",
                                      "senha123",
                                      LocalDateTime.now(),
                                      LocalDateTime.now(),
                                      usuarioPerfil);

        cliente = new Cliente(1L,
                              usuario,
                              "Nome Cliente",
                              LocalDate.of(1990, 1, 1),
                              "11999999999",
                              "cliente@test.com",
                              LocalDateTime.now(),
                              LocalDateTime.now());

        clienteEntity = ClienteMapper.toEntity(cliente);
    }

    @Test
    void deveCadastrarCliente() {
        clienteRepository.create(cliente);
        verify(clienteRepositoryJpa, times(1)).save(any(ClienteEntity.class));
    }

    @Test
    void deveAtualizarCliente() {
        clienteRepository.update(cliente);
        verify(clienteRepositoryJpa, times(1)).save(any(ClienteEntity.class));
    }

    @Test
    void deveExcluirCliente() {
        Long id = 1L;
        clienteRepository.delete(id);
        verify(clienteRepositoryJpa, times(1)).deleteById(id);
    }

    @Test
    void deveRetornarUmCliente() {
        when(clienteRepositoryJpa.findById(1L)).thenReturn(Optional.of(clienteEntity));
        Optional<Cliente> result = clienteRepository.findById(1L);
        assertTrue(result.isPresent());
        assertEquals(cliente.getId(), result.get().getId());
    }

    @Test
    void deveRetornarTodosCliente() {
        Pageable pageable = PageRequest.of(0, 10);
        List<ClienteEntity> entities = List.of(clienteEntity);
        Page<ClienteEntity> page = new PageImpl<>(entities);

        when(clienteRepositoryJpa.findAll(pageable)).thenReturn(page);

        List<Cliente> result = clienteRepository.findAll(pageable);
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
    }
}
