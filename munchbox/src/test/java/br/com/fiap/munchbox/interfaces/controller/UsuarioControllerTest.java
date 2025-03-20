package br.com.fiap.munchbox.interfaces.controller;

import br.com.fiap.munchbox.domain.core.Usuario;
import br.com.fiap.munchbox.domain.core.UsuarioPerfil;
import br.com.fiap.munchbox.interfaces.dto.UsuarioRequestDTO;
import br.com.fiap.munchbox.usecase.usuario.*;
import br.com.fiap.munchbox.usecase.usuarioperfil.ConsultarUmUsuarioPerfilUseCase;
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
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UsuarioControllerTest {

    @InjectMocks
    private UsuarioController usuarioController;

    @Mock
    private ConsultarUmUsuarioPerfilUseCase consultarUmUsuarioPerfilUseCase;

    @Mock
    private CadastrarUsuarioUseCase cadastrarUsuarioUseCase;

    @Mock
    private AtualizarUsuarioUseCase atualizarUsuarioUseCase;

    @Mock
    private RemoverUsuarioUseCase removerUsuarioUseCase;

    @Mock
    private ConsultarTodosUsuarioUseCase consultarTodosUsuarioUseCase;

    @Mock
    private ConsultarUmUsuarioUseCase consultarUmUsuarioUseCase;

    private Usuario usuario;
    private UsuarioPerfil usuarioPerfil;

    @BeforeEach
    void setUp() {
        usuarioPerfil = new UsuarioPerfil();
        usuarioPerfil.setId(1L);

        usuario = new Usuario();
        usuario.setId(1L);
        usuario.setLogin("usuario_teste");
        usuario.setSenha("senha_teste");
        usuario.setDataAtualizacao(LocalDateTime.now());
        usuario.setDataInclusao(LocalDateTime.now());
        usuario.setUsuarioPerfil(usuarioPerfil);
    }

    @Test
    void deveCadastrarUsuario() {
        UsuarioRequestDTO dto = new UsuarioRequestDTO("usuario_teste", "senha_teste", 1L);
        when(consultarUmUsuarioPerfilUseCase.execute(1L)).thenReturn(Optional.of(usuarioPerfil));

        ResponseEntity<Usuario> response = usuarioController.create(dto);

        assertEquals(201, response.getStatusCodeValue());
        verify(cadastrarUsuarioUseCase, times(1)).execute(any(Usuario.class));
    }

    @Test
    void deveLancarExcecao_QuandoUsuarioPerfilNaoExistir_AoCadastrarUsuario() {
        UsuarioRequestDTO dto = new UsuarioRequestDTO("usuario_teste", "senha_teste", 1L);
        when(consultarUmUsuarioPerfilUseCase.execute(1L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class,
                                                  () -> usuarioController.create(dto));
        assertEquals("Perfil de usuário não encontrado", exception.getMessage());
        verify(consultarUmUsuarioPerfilUseCase, times(1)).execute(anyLong());
        verify(cadastrarUsuarioUseCase, never()).execute(any(Usuario.class));
    }

    @Test
    void deveAtualizarUsuario() {
        UsuarioRequestDTO dto = new UsuarioRequestDTO("usuario_atualizado", "senha_atualizada", 1L);
        when(consultarUmUsuarioUseCase.execute(1L)).thenReturn(Optional.of(usuario));
        when(consultarUmUsuarioPerfilUseCase.execute(1L)).thenReturn(Optional.of(usuarioPerfil));

        ResponseEntity<Usuario> response = usuarioController.update(dto, 1L);

        assertEquals(200, response.getStatusCodeValue());
        verify(atualizarUsuarioUseCase, times(1)).execute(any(Usuario.class));
    }

    @Test
    void deveLancarExcecao_QuandoUsuarioPerfilNaoExistir_AoAtualizarUsuario() {
        UsuarioRequestDTO dto = new UsuarioRequestDTO("usuario_teste", "senha_teste", 1L);
        when(consultarUmUsuarioUseCase.execute(1L)).thenReturn(Optional.of(usuario));
        when(consultarUmUsuarioPerfilUseCase.execute(1L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class,
                                                  () -> usuarioController.update(dto, 1L));

        assertEquals("Perfil de usuário não encontrado", exception.getMessage());
        verify(consultarUmUsuarioPerfilUseCase, times(1)).execute(anyLong());
        verify(atualizarUsuarioUseCase, never()).execute(any(Usuario.class));
    }

    @Test
    void deveLancarExcecao_QuandoUsuarioNaoExistir_AoAtualizarUsuario() {
        UsuarioRequestDTO dto = new UsuarioRequestDTO("usuario_teste", "senha_teste", 1L);
        when(consultarUmUsuarioUseCase.execute(1L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class,
                                                  () -> usuarioController.update(dto, 1L));

        assertEquals("Usuário não encontrado", exception.getMessage());
        verify(consultarUmUsuarioUseCase, times(1)).execute(anyLong());
        verify(atualizarUsuarioUseCase, never()).execute(any(Usuario.class));
    }

    @Test
    void deveRemoverUsuario() {
        doNothing().when(removerUsuarioUseCase).execute(1L);
        ResponseEntity<Void> response = usuarioController.delete(1L);
        assertEquals(200, response.getStatusCodeValue());
        verify(removerUsuarioUseCase, times(1)).execute(1L);
    }

    @Test
    void deveConsultarTodosUsuarios() {
        when(consultarTodosUsuarioUseCase.execute(any(Pageable.class))).thenReturn(List.of(usuario));
        ResponseEntity<List<Usuario>> response = usuarioController.findAll(1, 15);
        assertEquals(200, response.getStatusCodeValue());
        assertFalse(response.getBody().isEmpty());
    }

    @Test
    void deveConsultarUmUsuario() {
        when(consultarUmUsuarioUseCase.execute(1L)).thenReturn(Optional.of(usuario));

        ResponseEntity<Optional<Usuario>> response = usuarioController.findById(1L);

        assertEquals(200, response.getStatusCodeValue());
        assertTrue(response.getBody().isPresent());
    }

    @Test
    void deveGravarLog_QuandoUsuarioNaoEncontrado_AoConsultarUmUsuario() {
        when(consultarUmUsuarioUseCase.execute(1L)).thenReturn(Optional.empty());

        ResponseEntity<Optional<Usuario>> response = usuarioController.findById(1L);

        assertEquals(200, response.getStatusCodeValue());
        assertFalse(response.getBody().isPresent());
    }
}
