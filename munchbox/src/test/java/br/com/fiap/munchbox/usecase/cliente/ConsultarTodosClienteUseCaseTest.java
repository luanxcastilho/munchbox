package br.com.fiap.munchbox.usecase.cliente;

import br.com.fiap.munchbox.domain.core.Cliente;
import br.com.fiap.munchbox.domain.gateway.ClienteGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class ConsultarTodosClienteUseCaseTest {

    @Mock
    private ClienteGateway clienteGateway; // Mock do gateway para evitar acesso ao banco de dados

    @InjectMocks
    private ConsultarTodosClienteUseCase consultarTodosClienteUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Deve retornar uma lista de clientes com sucesso")
    void deveRetornarListaDeClientesComSucesso() {

        // Arrange
        Pageable pageable = mock(Pageable.class);
        List<Cliente> clientes = List.of(new Cliente(), new Cliente());
        when(clienteGateway.findAll(pageable)).thenReturn(clientes);

        // Act
        List<Cliente> resultado = consultarTodosClienteUseCase.execute(pageable);

        // Assert
        assertNotNull(resultado, "A lista de clientes não deve ser nula");
        verify(clienteGateway, times(1)).findAll(pageable);
        System.out.println("Lista de clientes retornada com sucesso!");
    }
}
