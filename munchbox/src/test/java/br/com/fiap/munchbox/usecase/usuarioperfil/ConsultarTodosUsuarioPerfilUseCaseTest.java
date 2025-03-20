package br.com.fiap.munchbox.usecase.usuarioperfil;

import br.com.fiap.munchbox.domain.core.UsuarioPerfil;
import br.com.fiap.munchbox.domain.gateway.UsuarioPerfilGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ConsultarTodosUsuarioPerfilUseCaseTest {

    @Mock
    private UsuarioPerfilGateway usuarioPerfilGateway;

    @InjectMocks
    private ConsultarTodosUsuarioPerfilUseCase consultarTodosUsuarioPerfilUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveConsultarTodosUsuarioPerfis() {
        Pageable pageable = mock(Pageable.class);
        List<UsuarioPerfil> perfisEsperados = List.of(new UsuarioPerfil(), new UsuarioPerfil());

        when(usuarioPerfilGateway.findAll(pageable)).thenReturn(perfisEsperados);

        List<UsuarioPerfil> resultado = consultarTodosUsuarioPerfilUseCase.execute(pageable);

        assertEquals(perfisEsperados, resultado);
        verify(usuarioPerfilGateway).findAll(pageable);
    }
}
