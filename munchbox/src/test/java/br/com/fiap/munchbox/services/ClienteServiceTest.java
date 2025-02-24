package br.com.fiap.munchbox.services;

import br.com.fiap.munchbox.dtos.cliente.ClienteRequestDTO;
import br.com.fiap.munchbox.entities.Cliente;
import br.com.fiap.munchbox.entities.Usuario;
import br.com.fiap.munchbox.helpers.ClienteHelper;
import br.com.fiap.munchbox.helpers.UsuarioHelper;
import br.com.fiap.munchbox.repositories.ClienteRepository;
import br.com.fiap.munchbox.repositories.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.never;

public class ClienteServiceTest
{

    @Mock
    private ClienteRepository clienteRepository;

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private ClienteService clienteService;

    private Cliente cliente;
    private ClienteRequestDTO clienteRequestDTO;
    private Usuario usuario;

    @BeforeEach
    void setUp()
    {
        MockitoAnnotations.openMocks(this);

        cliente = ClienteHelper.gerarCliente();
        usuario = UsuarioHelper.gerarUsuario();

        clienteRequestDTO = new ClienteRequestDTO();

        clienteRequestDTO.setNome(cliente.getNome());
        clienteRequestDTO.setEmail(cliente.getEmail());
        clienteRequestDTO.setCelular(cliente.getCelular());
        clienteRequestDTO.setDataNascimento(cliente.getDataNascimento());
        clienteRequestDTO.setIdUsuario(usuario.getIdUsuario());
    }

    @Test
    void devePermitirConsultarTodosClientes()
    {
        Page<Cliente> page = new PageImpl<>(List.of(cliente));
        when(clienteRepository.findAll(any(PageRequest.class))).thenReturn(page);

        List<Cliente> resultado = clienteService.findAll(1, 15);

        verify(clienteRepository, times(1)).findAll(any(PageRequest.class));
    }

    @Test
    void devePermitirConsultarUmCliente()
    {
        when(clienteRepository.findById(cliente.getIdCliente())).thenReturn(Optional.of(cliente));

        Optional<Cliente> resultado = clienteService.findById(cliente.getIdCliente());

        verify(clienteRepository, times(1)).findById(any(Long.class));
    }

    @Test
    void devePermitirCriarCliente()
    {
        when(usuarioRepository.findById(usuario.getIdUsuario())).thenReturn(Optional.of(usuario));
        when(clienteRepository.save(any(Cliente.class))).thenReturn(cliente);

        clienteService.create(clienteRequestDTO);

        verify(clienteRepository, times(1)).save(any(Cliente.class));
    }

    @Test
    void devePermitirAtualizarCliente()
    {
        when(usuarioRepository.findById(usuario.getIdUsuario())).thenReturn(Optional.of(usuario));
        when(clienteRepository.findById(cliente.getIdCliente())).thenReturn(Optional.of(cliente));
        when(clienteRepository.save(any(Cliente.class))).thenReturn(cliente);

        clienteService.update(clienteRequestDTO, cliente.getIdCliente());

        verify(clienteRepository, times(1)).save(any(Cliente.class));
    }

    @Test
    void devePermitirRemoverCliente()
    {
        doNothing().when(clienteRepository).delete(any(Cliente.class));

        clienteService.delete(cliente.getIdCliente());

        verify(clienteRepository, times(1)).deleteById(cliente.getIdCliente());
    }

    @Test
    void deveLancarExcecao_SeUsuarioNaoExistir_AoCriarCliente()
    {
        clienteRequestDTO.setIdUsuario(999L);
        when(clienteRepository.findById(cliente.getIdCliente())).thenReturn(Optional.of(cliente));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> clienteService.create(clienteRequestDTO));

        assertEquals("Usuário não encontrado", exception.getMessage());
        verify(clienteRepository, never()).save(any(Cliente.class));
    }

    @Test
    void deveLancarExcecao_SeClienteNaoExistir_AoAtualizarCliente()
    {
        when(clienteRepository.findById(cliente.getIdCliente())).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> clienteService.update(clienteRequestDTO, cliente.getIdCliente()));

        assertEquals("Cliente não encontrado", exception.getMessage());
        verify(clienteRepository, never()).save(any(Cliente.class));
    }

    @Test
    void deveLancarExcecao_SeUsuarioNaoExistir_AoAtualizarCliente()
    {
        clienteRequestDTO.setIdUsuario(999L);
        when(clienteRepository.findById(cliente.getIdCliente())).thenReturn(Optional.of(cliente));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> clienteService.update(clienteRequestDTO, cliente.getIdCliente()));

        assertEquals("Usuário não encontrado", exception.getMessage());
        verify(clienteRepository, never()).save(any(Cliente.class));
    }
}
