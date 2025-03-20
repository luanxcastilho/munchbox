package br.com.fiap.munchbox.usecase.usuarioperfil;

import br.com.fiap.munchbox.domain.core.UsuarioPerfil;
import br.com.fiap.munchbox.domain.gateway.UsuarioPerfilGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

class AtualizarUsuarioPerfilUseCaseTest {

    @Mock
    private UsuarioPerfilGateway usuarioPerfilGateway;

    @InjectMocks
    private AtualizarUsuarioPerfilUseCase atualizarUsuarioPerfilUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveAtualizarUsuarioPerfil() {
        UsuarioPerfil usuarioPerfil = new UsuarioPerfil();

        atualizarUsuarioPerfilUseCase.execute(usuarioPerfil);

        verify(usuarioPerfilGateway).update(usuarioPerfil);
    }
}
