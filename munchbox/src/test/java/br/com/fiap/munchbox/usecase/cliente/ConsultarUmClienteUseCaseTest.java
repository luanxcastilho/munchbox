package br.com.fiap.munchbox.usecase.cliente;

import br.com.fiap.munchbox.domain.core.Cliente;
import br.com.fiap.munchbox.domain.gateway.ClienteGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ConsultarUmClienteUseCaseTest {
    @Mock
    private ClienteGateway clienteGateway;

    @InjectMocks
    private ConsultarUmClienteUseCase consultarUmClienteUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    @DisplayName("Deve retornar um cliente quando ID existir")
    void deveRetornarClienteQuandoIdExistir() {
        // Arrange
        Long id = 1L;
        Cliente cliente = new Cliente();
        when(clienteGateway.findById(id)).thenReturn(Optional.of(cliente));

        // Act
        Optional<Cliente> resultado = consultarUmClienteUseCase.execute(id);

        // Assert
        assertTrue(resultado.isPresent(), "O cliente deve estar presente");
        assertEquals(cliente, resultado.get(), "O cliente retornado deve ser o mesmo esperado");
        verify(clienteGateway, times(1)).findById(id);
        System.out.println("Cliente encontrado com sucesso!");
    }

    @Test
    @DisplayName("Deve retornar vazio quando ID não existir")
    void deveRetornarVazioQuandoIdNaoExistir() {
        // Arrange
        Long id = 2L;
        when(clienteGateway.findById(id)).thenReturn(Optional.empty());

        // Act
        Optional<Cliente> resultado = consultarUmClienteUseCase.execute(id);

        // Assert
        assertFalse(resultado.isPresent(), "O cliente não deve estar presente");
        verify(clienteGateway, times(1)).findById(id);
        System.out.println("Nenhum cliente encontrado para o ID fornecido!");
    }
}
