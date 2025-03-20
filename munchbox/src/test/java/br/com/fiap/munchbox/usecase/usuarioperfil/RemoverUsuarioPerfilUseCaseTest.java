package br.com.fiap.munchbox.usecase.usuarioperfil;

import br.com.fiap.munchbox.domain.gateway.UsuarioPerfilGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

class RemoverUsuarioPerfilUseCaseTest {

    @Mock
    private UsuarioPerfilGateway usuarioPerfilGateway;

    @InjectMocks
    private RemoverUsuarioPerfilUseCase removerUsuarioPerfilUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveRemoverUsuarioPerfilQuandoIdForValido() {
        Long id = 1L;

        removerUsuarioPerfilUseCase.execute(id);

        verify(usuarioPerfilGateway, times(1)).delete(id);
    }
}
