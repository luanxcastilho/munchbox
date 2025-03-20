package br.com.fiap.munchbox.interfaces.controller;

import br.com.fiap.munchbox.domain.core.Cliente;
import br.com.fiap.munchbox.domain.core.ClienteEndereco;
import br.com.fiap.munchbox.interfaces.dto.ClienteEnderecoRequestDTO;
import br.com.fiap.munchbox.usecase.cliente.ConsultarUmClienteUseCase;
import br.com.fiap.munchbox.usecase.clienteendereco.*;
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
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClienteEnderecoControllerTest {

    @InjectMocks
    private ClienteEnderecoController clienteEnderecoController;

    @Mock
    private CadastrarClienteEnderecoUseCase cadastrarClienteEnderecoUseCase;
    @Mock
    private AtualizarClienteEnderecoUseCase atualizarClienteEnderecoUseCase;
    @Mock
    private RemoverClienteEnderecoUseCase removerClienteEnderecoUseCase;
    @Mock
    private ConsultarTodosClienteEnderecoUseCase consultarTodosClienteEnderecoUseCase;
    @Mock
    private ConsultarUmClienteEnderecoUseCase consultarUmClienteEnderecoUseCase;
    @Mock
    private ConsultarUmClienteUseCase consultarUmClienteUseCase;

    private Cliente cliente;
    private ClienteEndereco clienteEndereco;

    @BeforeEach
    void setUp() {
        cliente = new Cliente();
        cliente.setId(1L);

        clienteEndereco = new ClienteEndereco();
        clienteEndereco.setId(1L);
        clienteEndereco.setCliente(cliente);
        clienteEndereco.setRua("Rua Teste");
        clienteEndereco.setNumero("100");
        clienteEndereco.setBairro("Centro");
        clienteEndereco.setCidade("Cidade Teste");
        clienteEndereco.setEstado("SP");
        clienteEndereco.setCep("12345-678");
        clienteEndereco.setDataInclusao(LocalDateTime.now());
        clienteEndereco.setDataAtualizacao(LocalDateTime.now());
    }

    @Test
    void deveCadastrarClienteEndereco() {

        ClienteEnderecoRequestDTO dto = new ClienteEnderecoRequestDTO(1L,
                                                                      "Rua Teste",
                                                                      "100",
                                                                      "Apto 1",
                                                                      "Centro",
                                                                      "Cidade Teste",
                                                                      "SP",
                                                                      "12345-678");

        when(consultarUmClienteUseCase.execute(anyLong())).thenReturn(Optional.of(cliente));
        doNothing().when(cadastrarClienteEnderecoUseCase).execute(any(ClienteEndereco.class));

        ResponseEntity<ClienteEndereco> response = clienteEnderecoController.create(dto);

        assertEquals(201, response.getStatusCodeValue());
        verify(cadastrarClienteEnderecoUseCase, times(1)).execute(any(ClienteEndereco.class));
    }

    @Test
    void deveLancarUmaExcecao_QuandoClienteNaoEncontrado_AoCadastrarClienteEndereco() {

        ClienteEnderecoRequestDTO dto = new ClienteEnderecoRequestDTO(1L,
                                                                      "Rua Teste",
                                                                      "100",
                                                                      "Apto 1",
                                                                      "Centro",
                                                                      "Cidade Teste",
                                                                      "SP",
                                                                      "12345-678");

        when(consultarUmClienteUseCase.execute(anyLong())).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> clienteEnderecoController.create(dto));

        assertEquals("Cliente não encontrado", exception.getMessage());
        verify(cadastrarClienteEnderecoUseCase, never()).execute(any(ClienteEndereco.class));
    }

    @Test
    void deveAtualizarClienteEndereco() {

        ClienteEnderecoRequestDTO dto = new ClienteEnderecoRequestDTO(1L,
                                                                      "Rua Nova",
                                                                      "200",
                                                                      "Apto 2",
                                                                      "Centro",
                                                                      "Cidade Nova",
                                                                      "SP",
                                                                      "98765-432");

        when(consultarUmClienteEnderecoUseCase.execute(anyLong())).thenReturn(Optional.of(clienteEndereco));
        when(consultarUmClienteUseCase.execute(anyLong())).thenReturn(Optional.of(cliente));
        doNothing().when(atualizarClienteEnderecoUseCase).execute(any(ClienteEndereco.class));

        ResponseEntity<ClienteEndereco> response = clienteEnderecoController.create(dto, 1L);

        assertEquals(200, response.getStatusCodeValue());
        verify(atualizarClienteEnderecoUseCase, times(1)).execute(any(ClienteEndereco.class));
    }

    @Test
    void deveLancarUmaExcecao_QuandoClienteEnderecoNaoEncontrado_AoAtualizarClienteEndereco() {

        ClienteEnderecoRequestDTO dto = new ClienteEnderecoRequestDTO(1L,
                                                                      "Rua Nova",
                                                                      "200",
                                                                      "Apto 2",
                                                                      "Centro",
                                                                      "Cidade Nova",
                                                                      "SP",
                                                                      "98765-432");

        when(consultarUmClienteEnderecoUseCase.execute(anyLong())).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class,
                                                  () -> clienteEnderecoController.create(dto, 1L));

        assertEquals("Endereço de cliente não encontrado", exception.getMessage());
        verify(atualizarClienteEnderecoUseCase, never()).execute(any(ClienteEndereco.class));
    }

    @Test
    void deveLancarUmaExcecao_QuandoClienteNaoEncontrado_AoAtualizarClienteEndereco() {

        ClienteEnderecoRequestDTO dto = new ClienteEnderecoRequestDTO(1L,
                                                                      "Rua Nova",
                                                                      "200",
                                                                      "Apto 2",
                                                                      "Centro",
                                                                      "Cidade Nova",
                                                                      "SP",
                                                                      "98765-432");

        when(consultarUmClienteEnderecoUseCase.execute(anyLong())).thenReturn(Optional.of(clienteEndereco));
        when(consultarUmClienteUseCase.execute(anyLong())).thenReturn(Optional.empty());


        RuntimeException exception = assertThrows(RuntimeException.class,
                                                  () -> clienteEnderecoController.create(dto, 1L));

        assertEquals("Cliente não encontrado", exception.getMessage());
        verify(atualizarClienteEnderecoUseCase, never()).execute(any(ClienteEndereco.class));
    }

    @Test
    void deveRemoverClienteEndereco() {

        doNothing().when(removerClienteEnderecoUseCase).execute(anyLong());

        ResponseEntity<Void> response = clienteEnderecoController.delete(1L);

        assertEquals(200, response.getStatusCodeValue());
        verify(removerClienteEnderecoUseCase, times(1)).execute(anyLong());
    }

    @Test
    void deveConsultarTodosClienteEnderecos() {

        when(consultarTodosClienteEnderecoUseCase.execute(any())).thenReturn(List.of(clienteEndereco));

        ResponseEntity<List<ClienteEndereco>> response = clienteEnderecoController.findAll(1, 10);

        assertEquals(200, response.getStatusCodeValue());
        assertFalse(response.getBody().isEmpty());
    }

    @Test
    void deveConsultarUmClienteEndereco() {

        when(consultarUmClienteEnderecoUseCase.execute(anyLong())).thenReturn(Optional.of(clienteEndereco));

        ResponseEntity<Optional<ClienteEndereco>> response = clienteEnderecoController.findById(1L);

        assertEquals(200, response.getStatusCodeValue());
        assertTrue(response.getBody().isPresent());
    }

    @Test
    void deveGravarLog_QuandoClienteEnderecoNaoExiste_AoConsultarUmClienteEndereco() {

        when(consultarUmClienteEnderecoUseCase.execute(anyLong())).thenReturn(Optional.empty());

        ResponseEntity<Optional<ClienteEndereco>> response = clienteEnderecoController.findById(1L);

        assertEquals(200, response.getStatusCodeValue());
        assertFalse(response.getBody().isPresent());
    }
}
