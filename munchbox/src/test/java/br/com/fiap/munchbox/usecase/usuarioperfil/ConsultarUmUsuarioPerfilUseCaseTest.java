package br.com.fiap.munchbox.usecase.usuarioperfil;

import br.com.fiap.munchbox.domain.core.UsuarioPerfil;
import br.com.fiap.munchbox.domain.gateway.UsuarioPerfilGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ConsultarUmUsuarioPerfilUseCaseTest {

    @Mock
    private UsuarioPerfilGateway usuarioPerfilGateway;

    @InjectMocks
    private ConsultarUmUsuarioPerfilUseCase consultarUmUsuarioPerfilUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveRetornarUsuarioPerfilQuandoEncontrado() {
        Long id = 1L;
        UsuarioPerfil usuarioPerfilEsperado = new UsuarioPerfil();
        when(usuarioPerfilGateway.findById(id)).thenReturn(Optional.of(usuarioPerfilEsperado));

        Optional<UsuarioPerfil> resultado = consultarUmUsuarioPerfilUseCase.execute(id);

        assertTrue(resultado.isPresent());
        assertEquals(usuarioPerfilEsperado, resultado.get());
        verify(usuarioPerfilGateway).findById(id);
    }

    @Test
    void deveRetornarVazioQuandoUsuarioPerfilNaoEncontrado() {
        Long id = 1L;
        when(usuarioPerfilGateway.findById(id)).thenReturn(Optional.empty());

        Optional<UsuarioPerfil> resultado = consultarUmUsuarioPerfilUseCase.execute(id);

        assertFalse(resultado.isPresent());
        verify(usuarioPerfilGateway).findById(id);
    }
}
