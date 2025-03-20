package br.com.fiap.munchbox.usecase.clienteendereco;

import br.com.fiap.munchbox.domain.core.ClienteEndereco;
import br.com.fiap.munchbox.domain.gateway.ClienteEnderecoGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ConsultarTodosClienteEnderecoUseCaseTest {

    @Mock
    private ClienteEnderecoGateway clienteEnderecoGateway;

    @InjectMocks
    private ConsultarTodosClienteEnderecoUseCase consultarTodosClienteEnderecoUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveConsultarTodosClienteEndereco() {
        Pageable pageable = mock(Pageable.class);
        List<ClienteEndereco> clienteEnderecos = List.of(new ClienteEndereco(), new ClienteEndereco());
        when(clienteEnderecoGateway.findAll(pageable)).thenReturn(clienteEnderecos);

        List<ClienteEndereco> resultado = consultarTodosClienteEnderecoUseCase.execute(pageable);

        assertEquals(clienteEnderecos, resultado);
        verify(clienteEnderecoGateway).findAll(pageable);
    }
}