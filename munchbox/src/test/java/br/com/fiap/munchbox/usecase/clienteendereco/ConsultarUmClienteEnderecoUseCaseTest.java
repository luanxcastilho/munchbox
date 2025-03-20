package br.com.fiap.munchbox.usecase.clienteendereco;

import br.com.fiap.munchbox.domain.core.ClienteEndereco;
import br.com.fiap.munchbox.domain.gateway.ClienteEnderecoGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class ConsultarUmClienteEnderecoUseCaseTest {

    @Mock
    private ClienteEnderecoGateway clienteEnderecoGateway;

    @InjectMocks
    private ConsultarUmClienteEnderecoUseCase consultarUmClienteEnderecoUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveConsultarUmClienteEndereco() {
        Long id = 1L;
        ClienteEndereco clienteEndereco = new ClienteEndereco();
        when(clienteEnderecoGateway.findById(id)).thenReturn(Optional.of(clienteEndereco));

        Optional<ClienteEndereco> resultado = consultarUmClienteEnderecoUseCase.execute(id);

        assertTrue(resultado.isPresent());
        assertEquals(clienteEndereco, resultado.get());
        verify(clienteEnderecoGateway).findById(id);
    }
}