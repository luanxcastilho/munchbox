package br.com.fiap.munchbox.usecase.cliente;

import br.com.fiap.munchbox.domain.gateway.ClienteGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.stereotype.Component;

import static org.mockito.Mockito.*;

@Component
public class RemoverClienteUseCaseTest
{
    @Mock
    private ClienteGateway clienteGateway;

    @InjectMocks
    private RemoverClienteUseCase removerClienteUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Deve remover um cliente com sucesso")
    void deveRemoverClienteComSucesso() {
        // Arrange
        Long id = 1L;
        doNothing().when(clienteGateway).delete(id);

        // Act
        removerClienteUseCase.execute(id);

        // Assert
        verify(clienteGateway, times(1)).delete(id);
        System.out.println("✅ Cliente removido com sucesso!");
    }
}
