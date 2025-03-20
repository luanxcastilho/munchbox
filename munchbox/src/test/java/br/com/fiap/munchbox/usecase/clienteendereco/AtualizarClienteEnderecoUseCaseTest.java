package br.com.fiap.munchbox.usecase.clienteendereco;

import br.com.fiap.munchbox.domain.core.ClienteEndereco;
import br.com.fiap.munchbox.domain.gateway.ClienteEnderecoGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

class AtualizarClienteEnderecoUseCaseTest {

    @Mock
    private ClienteEnderecoGateway clienteEnderecoGateway;

    @InjectMocks
    private AtualizarClienteEnderecoUseCase atualizarClienteEnderecoUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveAtualizarClienteEndereco() {
        ClienteEndereco clienteEndereco = new ClienteEndereco();

        atualizarClienteEnderecoUseCase.execute(clienteEndereco);

        verify(clienteEnderecoGateway).update(clienteEndereco);
    }
}
