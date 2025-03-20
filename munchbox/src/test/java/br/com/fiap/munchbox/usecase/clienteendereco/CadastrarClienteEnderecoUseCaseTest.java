package br.com.fiap.munchbox.usecase.clienteendereco;

import br.com.fiap.munchbox.domain.core.ClienteEndereco;
import br.com.fiap.munchbox.domain.gateway.ClienteEnderecoGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

class CadastrarClienteEnderecoUseCaseTest {

    @Mock
    private ClienteEnderecoGateway clienteEnderecoGateway;

    @InjectMocks
    private CadastrarClienteEnderecoUseCase cadastrarClienteEnderecoUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveCadastrarClienteEndereco() {
        ClienteEndereco clienteEndereco = new ClienteEndereco();

        cadastrarClienteEnderecoUseCase.execute(clienteEndereco);

        verify(clienteEnderecoGateway).create(clienteEndereco);
    }
}