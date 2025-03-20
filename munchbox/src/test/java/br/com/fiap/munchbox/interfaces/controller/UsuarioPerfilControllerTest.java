package br.com.fiap.munchbox.interfaces.controller;

import br.com.fiap.munchbox.domain.core.UsuarioPerfil;
import br.com.fiap.munchbox.interfaces.dto.UsuarioPerfilRequestDTO;
import br.com.fiap.munchbox.usecase.usuarioperfil.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UsuarioPerfilControllerTest {

    @InjectMocks
    private UsuarioPerfilController usuarioPerfilController;

    @Mock
    private CadastrarUsuarioPerfilUseCase cadastrarUsuarioPerfilUseCase;

    @Mock
    private AtualizarUsuarioPerfilUseCase atualizarUsuarioPerfilUseCase;

    @Mock
    private RemoverUsuarioPerfilUseCase removerUsuarioPerfilUseCase;

    @Mock
    private ConsultarTodosUsuarioPerfilUseCase consultarTodosUsuarioPerfilUseCase;

    @Mock
    private ConsultarUmUsuarioPerfilUseCase consultarUmUsuarioPerfilUseCase;

    private UsuarioPerfil usuarioPerfil;

    @BeforeEach
    void setUp() {
        usuarioPerfil = new UsuarioPerfil();
        usuarioPerfil.setId(1L);
        usuarioPerfil.setNome("Perfil Teste");
        usuarioPerfil.setDataAtualizacao(LocalDateTime.now());
        usuarioPerfil.setDataInclusao(LocalDateTime.now());
    }

    @Test
    void deveCadastrarUsuarioPerfil() {
        UsuarioPerfilRequestDTO dto = new UsuarioPerfilRequestDTO("Perfil Teste");

        ResponseEntity<UsuarioPerfil> response = usuarioPerfilController.create(dto);

        assertEquals(201, response.getStatusCodeValue());
        verify(cadastrarUsuarioPerfilUseCase, times(1)).execute(any(UsuarioPerfil.class));
    }

    @Test
    void deveAtualizarUsuarioPerfil() {
        UsuarioPerfilRequestDTO dto = new UsuarioPerfilRequestDTO("Perfil Atualizado");
        when(consultarUmUsuarioPerfilUseCase.execute(1L)).thenReturn(Optional.of(usuarioPerfil));

        ResponseEntity<UsuarioPerfil> response = usuarioPerfilController.update(dto, 1L);

        assertEquals(200, response.getStatusCodeValue());
        verify(atualizarUsuarioPerfilUseCase, times(1)).execute(any(UsuarioPerfil.class));
    }

    @Test
    void deveLancarExcecao_QuandoUsuarioPerfilNaoEncontrado_AoAtualizarUmUsuarioPerfil() {

        UsuarioPerfilRequestDTO dto = new UsuarioPerfilRequestDTO("Perfil Atualizado");
        when(consultarUmUsuarioPerfilUseCase.execute(1L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class,
                                                  () -> usuarioPerfilController.update(dto, 1L));

        assertEquals("Perfil de usuário não encontrado", exception.getMessage());
        verify(consultarUmUsuarioPerfilUseCase, times(1)).execute(anyLong());
        verify(atualizarUsuarioPerfilUseCase, never()).execute(any(UsuarioPerfil.class));
    }

    @Test
    void deveRemoverUsuarioPerfil() {
        doNothing().when(removerUsuarioPerfilUseCase).execute(1L);

        ResponseEntity<Void> response = usuarioPerfilController.delete(1L);

        assertEquals(200, response.getStatusCodeValue());
        verify(removerUsuarioPerfilUseCase, times(1)).execute(1L);
    }

    @Test
    void deveConsultarTodosUsuarioPerfis() {
        when(consultarTodosUsuarioPerfilUseCase.execute(any(Pageable.class))).thenReturn(List.of(usuarioPerfil));

        ResponseEntity<List<UsuarioPerfil>> response = usuarioPerfilController.findAll(1, 15);

        assertEquals(200, response.getStatusCodeValue());
        assertFalse(response.getBody().isEmpty());
    }

    @Test
    void deveConsultarUmUsuarioPerfil() {
        when(consultarUmUsuarioPerfilUseCase.execute(1L)).thenReturn(Optional.of(usuarioPerfil));

        ResponseEntity<Optional<UsuarioPerfil>> response = usuarioPerfilController.findById(1L);

        assertEquals(200, response.getStatusCodeValue());
        assertTrue(response.getBody().isPresent());
    }

    @Test
    void deveGravarLog_QuandoUsuarioPerfilNaoEncontrado_AoConsultarUmUsuarioPerfil() {
        when(consultarUmUsuarioPerfilUseCase.execute(1L)).thenReturn(Optional.empty());

        ResponseEntity<Optional<UsuarioPerfil>> response = usuarioPerfilController.findById(1L);

        assertEquals(200, response.getStatusCodeValue());
        assertFalse(response.getBody().isPresent());
    }


}
