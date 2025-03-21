package br.com.fiap.munchbox.infrastructure.repository;

import br.com.fiap.munchbox.domain.core.Cliente;
import br.com.fiap.munchbox.domain.core.ClienteEndereco;
import br.com.fiap.munchbox.domain.core.Usuario;
import br.com.fiap.munchbox.domain.core.UsuarioPerfil;
import br.com.fiap.munchbox.infrastructure.entity.ClienteEnderecoEntity;
import br.com.fiap.munchbox.infrastructure.mapper.ClienteEnderecoMapper;
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
class ClienteEnderecoRepositoryTest {

    @Mock
    private ClienteEnderecoRepositoryJpa clienteEnderecoRepositoryJpa;

    @InjectMocks
    private ClienteEnderecoRepository clienteEnderecoRepository;

    private ClienteEndereco clienteEndereco;
    private ClienteEnderecoEntity clienteEnderecoEntity;

    @BeforeEach
    void setUp() {
        UsuarioPerfil usuarioPerfil = new UsuarioPerfil(1L, "Admnistrador", LocalDateTime.now(), LocalDateTime.now());

        Usuario usuario = new Usuario(1L,
                                      "usuario@test.com",
                                      "senha123",
                                      LocalDateTime.now(),
                                      LocalDateTime.now(),
                                      usuarioPerfil);

        Cliente cliente = new Cliente(1L,
                                      usuario,
                                      "Nome Cliente",
                                      LocalDate.of(1990, 1, 1),
                                      "11999999999",
                                      "cliente@test.com",
                                      LocalDateTime.now(),
                                      LocalDateTime.now());

        clienteEndereco = new ClienteEndereco(1L,
                                              cliente,
                                              "Rua Teste",
                                              "123",
                                              "Complemento",
                                              "Bairro",
                                              "Cidade",
                                              "Estado",
                                              "12345-678",
                                              LocalDateTime.now(),
                                              LocalDateTime.now());

        clienteEnderecoEntity = ClienteEnderecoMapper.toEntity(clienteEndereco);
    }

    @Test
    void deveCadastrarClienteEndereco() {
        clienteEnderecoRepository.create(clienteEndereco);
        verify(clienteEnderecoRepositoryJpa, times(1)).save(any(ClienteEnderecoEntity.class));
    }

    @Test
    void deveAtualizarClienteEndereco() {
        clienteEnderecoRepository.update(clienteEndereco);
        verify(clienteEnderecoRepositoryJpa, times(1)).save(any(ClienteEnderecoEntity.class));
    }

    @Test
    void deveExcluirClienteEndereco() {
        Long id = 1L;
        clienteEnderecoRepository.delete(id);
        verify(clienteEnderecoRepositoryJpa, times(1)).deleteById(id);
    }

    @Test
    void deveRetornarUmClienteEndereco() {
        when(clienteEnderecoRepositoryJpa.findById(1L)).thenReturn(Optional.of(clienteEnderecoEntity));
        Optional<ClienteEndereco> result = clienteEnderecoRepository.findById(1L);
        assertTrue(result.isPresent());
        assertEquals(clienteEndereco.getId(), result.get().getId());
    }

    @Test
    void deveRetornarTodosClienteEnderecos() {
        Pageable pageable = PageRequest.of(0, 10);
        List<ClienteEnderecoEntity> entities = List.of(clienteEnderecoEntity);
        Page<ClienteEnderecoEntity> page = new PageImpl<>(entities);

        when(clienteEnderecoRepositoryJpa.findAll(pageable)).thenReturn(page);

        List<ClienteEndereco> result = clienteEnderecoRepository.findAll(pageable);
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
    }
}