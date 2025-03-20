package br.com.fiap.munchbox.interfaces.controller;

import br.com.fiap.munchbox.domain.core.Cliente;
import br.com.fiap.munchbox.domain.core.Usuario;
import br.com.fiap.munchbox.interfaces.dto.ClienteRequestDTO;
import br.com.fiap.munchbox.usecase.cliente.*;
import br.com.fiap.munchbox.usecase.usuario.ConsultarUmUsuarioUseCase;
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
class ClienteControllerTest {

    @InjectMocks
    private ClienteController clienteController;

    @Mock
    private CadastrarClienteUseCase cadastrarClienteUseCase;
    @Mock
    private AtualizarClienteUseCase atualizarClienteUseCase;
    @Mock
    private RemoverClienteUseCase removerClienteUseCase;
    @Mock
    private ConsultarTodosClienteUseCase consultarTodosClienteUseCase;
    @Mock
    private ConsultarUmClienteUseCase consultarUmClienteUseCase;
    @Mock
    private ConsultarUmUsuarioUseCase consultarUmUsuarioUseCase;

    private Cliente cliente;
    private Usuario usuario;

    @BeforeEach
    void setUp() {
        usuario = new Usuario();
        usuario.setId(1L);
        usuario.setLogin("teste_user");

        cliente = new Cliente();
        cliente.setId(1L);
        cliente.setNome("Cliente Teste");
        cliente.setDataNascimento(LocalDate.of(1990, 1, 1));
        cliente.setCelular("11999999999");
        cliente.setEmail("cliente@teste.com");
        cliente.setDataInclusao(LocalDateTime.now());
        cliente.setDataAtualizacao(LocalDateTime.now());
        cliente.setUsuario(usuario);
    }

    @Test
    void deveCadastrarCliente() {

        ClienteRequestDTO dto = new ClienteRequestDTO(1L,
                                                      "Cliente Teste",
                                                      LocalDate.of(1990, 1, 1),
                                                      "11999999999",
                                                      "cliente@teste.com");

        when(consultarUmUsuarioUseCase.execute(anyLong())).thenReturn(Optional.of(usuario));
        doNothing().when(cadastrarClienteUseCase).execute(any(Cliente.class));

        ResponseEntity<Cliente> response = clienteController.create(dto);

        assertEquals(201, response.getStatusCodeValue());
        verify(cadastrarClienteUseCase, times(1)).execute(any(Cliente.class));
    }

    @Test
    void deveLancarExcecao_QuandoUsuarioNaoEncontrado_AoCadastrarCliente() {

        ClienteRequestDTO dto = new ClienteRequestDTO(1L,
                                                      "Cliente Teste",
                                                      LocalDate.of(1990, 1, 1),
                                                      "11999999999",
                                                      "cliente@teste.com");

        when(consultarUmUsuarioUseCase.execute(anyLong())).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            clienteController.create(dto);
        });

        assertEquals("Usuário não encontrado", exception.getMessage());
        verify(consultarUmUsuarioUseCase, times(1)).execute(anyLong());
        verify(cadastrarClienteUseCase, never()).execute(any(Cliente.class));
    }

    @Test
    void deveAtualizarCliente() {

        ClienteRequestDTO dto = new ClienteRequestDTO(1L,
                                                      "Cliente Teste",
                                                      LocalDate.of(1990, 1, 1),
                                                      "11999999999",
                                                      "cliente@teste.com");

        when(consultarUmClienteUseCase.execute(anyLong())).thenReturn(Optional.of(cliente));
        when(consultarUmUsuarioUseCase.execute(anyLong())).thenReturn(Optional.of(usuario));
        doNothing().when(atualizarClienteUseCase).execute(any(Cliente.class));

        ResponseEntity<Cliente> response = clienteController.update(dto, 1L);

        assertEquals(200, response.getStatusCodeValue());
        verify(atualizarClienteUseCase, times(1)).execute(any(Cliente.class));
    }

    @Test
    void deveLancarExcecao_QuandoUsuarioNaoEncontrado_AoAtualizarCliente() {

        ClienteRequestDTO dto = new ClienteRequestDTO(1L,
                                                      "Cliente Teste",
                                                      LocalDate.of(1990, 1, 1),
                                                      "11999999999",
                                                      "cliente@teste.com");

        when(consultarUmClienteUseCase.execute(anyLong())).thenReturn(Optional.of(cliente));
        when(consultarUmUsuarioUseCase.execute(anyLong())).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            clienteController.update(dto, 1L);
        });

        assertEquals("Usuário não encontrado", exception.getMessage());
        verify(consultarUmUsuarioUseCase, times(1)).execute(anyLong());
        verify(atualizarClienteUseCase, never()).execute(any(Cliente.class));
    }

    @Test
    void deveLancarExcecao_QuandoClienteNaoEncontrado_AoAtualizarCliente() {

        ClienteRequestDTO dto = new ClienteRequestDTO(1L,
                                                      "Cliente Teste",
                                                      LocalDate.of(1990, 1, 1),
                                                      "11999999999",
                                                      "cliente@teste.com");

        when(consultarUmClienteUseCase.execute(anyLong())).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            clienteController.update(dto, 1l);
        });

        assertEquals("Cliente não encontrado", exception.getMessage());
        verify(consultarUmClienteUseCase, times(1)).execute(anyLong());
        verify(atualizarClienteUseCase, never()).execute(any(Cliente.class));
    }

    @Test
    void deveConsultarUmCliente() {

        when(consultarUmClienteUseCase.execute(anyLong())).thenReturn(Optional.of(cliente));

        ResponseEntity<Optional<Cliente>> response = clienteController.findById(1L);

        assertEquals(200, response.getStatusCodeValue());
        assertTrue(response.getBody().isPresent());
        assertEquals("Cliente Teste", response.getBody().get().getNome());
    }

    @Test
    void deveGravarLog_QuandoClienteNaoEncontrado_AoConsultarUmCliente() {

        when(consultarUmClienteUseCase.execute(anyLong())).thenReturn(Optional.empty());

        ResponseEntity<Optional<Cliente>> response = clienteController.findById(1L);

        assertEquals(200, response.getStatusCodeValue());
        assertFalse(response.getBody().isPresent());
    }

    @Test
    void deveConsultarTodosClientes() {

        when(consultarTodosClienteUseCase.execute(any())).thenReturn(List.of(cliente));

        ResponseEntity<List<Cliente>> response = clienteController.findAll(1, 10);

        assertEquals(200, response.getStatusCodeValue());
        assertFalse(response.getBody().isEmpty());
    }


    @Test
    void deveRemoverCliente() {

        doNothing().when(removerClienteUseCase).execute(anyLong());

        ResponseEntity<Void> response = clienteController.delete(1L);

        assertEquals(200, response.getStatusCodeValue());
        verify(removerClienteUseCase, times(1)).execute(anyLong());
    }
}
