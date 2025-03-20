package br.com.fiap.munchbox.usecase.usuarioperfil;

import br.com.fiap.munchbox.domain.core.UsuarioPerfil;
import br.com.fiap.munchbox.domain.gateway.UsuarioPerfilGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

class CadastrarUsuarioPerfilUseCaseTest {

    @Mock
    private UsuarioPerfilGateway usuarioPerfilGateway;

    @InjectMocks
    private CadastrarUsuarioPerfilUseCase cadastrarUsuarioPerfilUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveCadastrarUsuarioPerfil() {
        UsuarioPerfil usuarioPerfil = new UsuarioPerfil();

        cadastrarUsuarioPerfilUseCase.execute(usuarioPerfil);

        verify(usuarioPerfilGateway).create(usuarioPerfil);
    }
}
