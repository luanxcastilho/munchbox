package br.com.fiap.munchbox.usecase.clienteendereco;

import br.com.fiap.munchbox.domain.gateway.ClienteEnderecoGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

class RemoverClienteEnderecoUseCaseTest {

    @Mock
    private ClienteEnderecoGateway clienteEnderecoGateway;

    @InjectMocks
    private RemoverClienteEnderecoUseCase removerClienteEnderecoUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveRemoverClienteEndereco() {
        Long id = 1L;

        removerClienteEnderecoUseCase.execute(id);

        verify(clienteEnderecoGateway).delete(id);
    }
}
