package br.com.fiap.munchbox.services;

import br.com.fiap.munchbox.dtos.clienteendereco.ClienteEnderecoRequestDTO;
import br.com.fiap.munchbox.entities.Cliente;
import br.com.fiap.munchbox.entities.ClienteEndereco;
import br.com.fiap.munchbox.helpers.ClienteEnderecoHelper;
import br.com.fiap.munchbox.helpers.ClienteHelper;
import br.com.fiap.munchbox.repositories.ClienteEnderecoRepository;
import br.com.fiap.munchbox.repositories.ClienteRepository;
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

public class ClienteEnderecoServiceTest
{
    @Mock
    private ClienteEnderecoRepository clienteEnderecoRepository;

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteEnderecoService clienteEnderecoService;

    ClienteEndereco clienteEndereco;
    ClienteEnderecoRequestDTO clienteEnderecoRequestDTO;
    Cliente cliente;

    @BeforeEach
    void setUp()
    {
        MockitoAnnotations.openMocks(this);

        clienteEndereco = ClienteEnderecoHelper.gerarClienteEndereco();
        cliente = ClienteHelper.gerarCliente();

        clienteEnderecoRequestDTO = new ClienteEnderecoRequestDTO();

        clienteEnderecoRequestDTO.setIdCliente(cliente.getIdCliente());
        clienteEnderecoRequestDTO.setRua(clienteEndereco.getRua());
        clienteEnderecoRequestDTO.setNumero(clienteEndereco.getNumero());
        clienteEnderecoRequestDTO.setComplemento(clienteEndereco.getComplemento());
        clienteEnderecoRequestDTO.setCidade(clienteEndereco.getCidade());
        clienteEnderecoRequestDTO.setEstado(clienteEndereco.getEstado());
        clienteEnderecoRequestDTO.setCep(clienteEndereco.getCep());
    }

    @Test
    void devePermitirConsultarTodosClienteEndereco()
    {
        Page<ClienteEndereco> page = new PageImpl<>(List.of(clienteEndereco));
        when(clienteEnderecoRepository.findAll(any(PageRequest.class))).thenReturn(page);

        List<ClienteEndereco> resultado = clienteEnderecoService.findAll(1, 15);

        verify(clienteEnderecoRepository, times(1)).findAll(any(PageRequest.class));
    }

    @Test
    void devePermitirConsultarUmClienteEndereco()
    {
        when(clienteEnderecoRepository.findById(clienteEndereco.getIdClienteEndereco())).thenReturn(Optional.of(clienteEndereco));

        Optional<ClienteEndereco> resultado = clienteEnderecoService.findById(clienteEndereco.getIdClienteEndereco());

        verify(clienteEnderecoRepository, times(1)).findById(any(Long.class));
    }

    @Test
    void devePermitirCriarClienteEndereco()
    {
        when(clienteRepository.findById(cliente.getIdCliente())).thenReturn(Optional.of(cliente));
        when(clienteEnderecoRepository.save(any(ClienteEndereco.class))).thenReturn(clienteEndereco);

        clienteEnderecoService.create(clienteEnderecoRequestDTO);

        verify(clienteEnderecoRepository, times(1)).save(any(ClienteEndereco.class));
    }

    @Test
    void devePermitirAtualizarClienteEndereco()
    {
        when(clienteRepository.findById(cliente.getIdCliente())).thenReturn(Optional.of(cliente));
        when(clienteEnderecoRepository.findById(clienteEndereco.getIdClienteEndereco())).thenReturn(Optional.of(clienteEndereco));
        when(clienteEnderecoRepository.save(any(ClienteEndereco.class))).thenReturn(clienteEndereco);

        clienteEnderecoService.update(clienteEnderecoRequestDTO, clienteEndereco.getIdClienteEndereco());

        verify(clienteEnderecoRepository, times(1)).save(any(ClienteEndereco.class));
    }

    @Test
    void devePermitirRemoverClienteEndereco()
    {
        doNothing().when(clienteEnderecoRepository).delete(any(ClienteEndereco.class));

        clienteEnderecoService.delete(clienteEndereco.getIdClienteEndereco());

        verify(clienteEnderecoRepository, times(1)).deleteById(clienteEndereco.getIdClienteEndereco());
    }

    @Test
    void deveLancarExcecao_SeClienteNaoExistir_AoCriarClienteEndereco()
    {
        clienteEnderecoRequestDTO.setIdCliente(999L);
        when(clienteEnderecoRepository.findById(clienteEndereco.getIdClienteEndereco())).thenReturn(Optional.of(clienteEndereco));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> clienteEnderecoService.create(clienteEnderecoRequestDTO));

        assertEquals("Cliente não encontrado", exception.getMessage());
        verify(clienteEnderecoRepository, never()).save(any(ClienteEndereco.class));
    }

    @Test
    void deveLancarExcecao_SeClienteEnderecoNaoExistir_AoAtualizarClienteEndereco()
    {
        when(clienteEnderecoRepository.findById(clienteEndereco.getIdClienteEndereco())).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> clienteEnderecoService.update(clienteEnderecoRequestDTO, clienteEndereco.getIdClienteEndereco()));

        assertEquals("Endereço do cliente não encontrado", exception.getMessage());
        verify(clienteEnderecoRepository, never()).save(any(ClienteEndereco.class));
    }

    @Test
    void deveLancarExcecao_SeClienteNaoExistir_AoAtualizarClienteEndereco()
    {
        clienteEnderecoRequestDTO.setIdCliente(999L);
        when(clienteEnderecoRepository.findById(clienteEndereco.getIdClienteEndereco())).thenReturn(Optional.of(clienteEndereco));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> clienteEnderecoService.update(clienteEnderecoRequestDTO, clienteEndereco.getIdClienteEndereco()));

        assertEquals("Cliente não encontrado", exception.getMessage());
        verify(clienteEnderecoRepository, never()).save(any(ClienteEndereco.class));
    }

}
