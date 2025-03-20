package br.com.fiap.munchbox.usecase.usuario;

import br.com.fiap.munchbox.domain.core.Usuario;
import br.com.fiap.munchbox.domain.gateway.UsuarioGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class ConsultarUmUsuarioUseCaseTest {

    @Mock
    private UsuarioGateway usuarioGateway;

    @InjectMocks
    private ConsultarUmUsuarioUseCase consultarUmUsuarioUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveConsultarUmUsuarioPorId() {
        Long id = 1L;
        Usuario usuario = new Usuario();
        usuario.setId(id);

        when(usuarioGateway.findById(id)).thenReturn(Optional.of(usuario));

        Optional<Usuario> resultado = consultarUmUsuarioUseCase.execute(id);

        assertTrue(resultado.isPresent());
        assertEquals(id, resultado.get().getId());
        verify(usuarioGateway).findById(id);
    }

    @Test
    void deveRetornarVazioQuandoUsuarioNaoExiste() {
        Long id = 2L;

        when(usuarioGateway.findById(id)).thenReturn(Optional.empty());

        Optional<Usuario> resultado = consultarUmUsuarioUseCase.execute(id);

        assertTrue(resultado.isEmpty());
        verify(usuarioGateway).findById(id);
    }
}
