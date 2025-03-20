package br.com.fiap.munchbox.usecase.cliente;

import br.com.fiap.munchbox.domain.core.Cliente;
import br.com.fiap.munchbox.domain.gateway.ClienteGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class AtualizarClienteUseCaseTest {

    @Mock
    private ClienteGateway clienteGateway;

    @InjectMocks
    private AtualizarClienteUseCase atualizarClienteUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Deve permitir atualizar um cliente")
    void deveAtualizarClienteComSucesso() {

        Cliente cliente = new Cliente();

        atualizarClienteUseCase.execute(cliente);

        verify(clienteGateway, times(1)).update(cliente);
    }
}
