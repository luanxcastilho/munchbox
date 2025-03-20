package br.com.fiap.munchbox.usecase.usuario;

import br.com.fiap.munchbox.domain.gateway.UsuarioGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

class RemoverUsuarioUseCaseTest {

    @Mock
    private UsuarioGateway usuarioGateway;

    @InjectMocks
    private RemoverUsuarioUseCase removerUsuarioUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveRemoverUsuarioPorId() {
        Long id = 1L;

        removerUsuarioUseCase.execute(id);

        verify(usuarioGateway).delete(id);
    }
}
