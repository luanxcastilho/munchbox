package br.com.fiap.munchbox.services;

import br.com.fiap.munchbox.dtos.usuario.UsuarioRequestDTO;
import br.com.fiap.munchbox.entities.Usuario;
import br.com.fiap.munchbox.entities.UsuarioPerfil;
import br.com.fiap.munchbox.helpers.UsuarioHelper;
import br.com.fiap.munchbox.helpers.UsuarioPerfilHelper;
import br.com.fiap.munchbox.repositories.UsuarioPerfilRepository;
import br.com.fiap.munchbox.repositories.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


class UsuarioServiceTest
{

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private UsuarioPerfilRepository usuarioPerfilRepository;

    @InjectMocks
    private UsuarioService usuarioService;

    @InjectMocks
    UsuarioPerfil usuarioPerfil;

    private Usuario usuario;
    private UsuarioRequestDTO usuarioRequestDTO;

    @BeforeEach
    void setUp()
    {
        MockitoAnnotations.openMocks(this);

        usuario = UsuarioHelper.gerarUsuario();
        usuarioPerfil = UsuarioPerfilHelper.gerarUsuarioPerfil();

        usuarioRequestDTO = new UsuarioRequestDTO();
        usuarioRequestDTO.setLogin(usuario.getLogin());
        usuarioRequestDTO.setSenha(usuario.getSenha());
        usuarioRequestDTO.setIdUsuarioPerfil(usuarioPerfil.getIdUsuarioPerfil());
    }

    @Test
    void devePermitirConsultarTodosUsuario()
    {
        Page<Usuario> page = new PageImpl<>(List.of(usuario));
        when(usuarioRepository.findAll(any(PageRequest.class))).thenReturn(page);

        List<Usuario> resultado = usuarioService.findAll(1, 15);

        assertFalse(resultado.isEmpty());
        assertEquals(1, resultado.size());
        assertEquals(usuario.getLogin(), resultado.getFirst().getLogin());

        verify(usuarioRepository, times(1)).findAll(any(PageRequest.class));
    }

    @Test
    void devePermitirConsultarUmUsuario()
    {
        when(usuarioRepository.findById(usuario.getIdUsuario())).thenReturn(Optional.of(usuario));

        Optional<Usuario> resultado = usuarioService.findById(usuario.getIdUsuario());

        assertFalse(resultado.isEmpty());
        assertEquals(usuario.getLogin(), resultado.get().getLogin());

        verify(usuarioRepository, times(1)).findById(usuario.getIdUsuario());
    }

    @Test
    void devePermitirCriarUsuario()
    {
        when(usuarioPerfilRepository.findById(usuarioPerfil.getIdUsuarioPerfil())).thenReturn(Optional.of(usuarioPerfil));
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuario);

        usuarioService.create(usuarioRequestDTO);

        verify(usuarioRepository, times(1)).save(any(Usuario.class));
    }

    @Test
    void devePermitirAtualizarUsuario()
    {
        when(usuarioPerfilRepository.findById(usuarioPerfil.getIdUsuarioPerfil())).thenReturn(Optional.of(usuarioPerfil));
        when(usuarioRepository.findById(usuario.getIdUsuario())).thenReturn(Optional.of(usuario));
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuario);

        usuarioService.update(usuarioRequestDTO, usuario.getIdUsuario());

        verify(usuarioRepository, times(1)).save(any(Usuario.class));
    }

    @Test
    void devePermitirRemoverUsuario()
    {
        doNothing().when(usuarioRepository).deleteById(usuario.getIdUsuario());

        usuarioService.delete(usuario.getIdUsuario());

        verify(usuarioRepository, times(1)).deleteById(usuario.getIdUsuario());
    }

    @Test
    void deveLancarExcecao_SeUsuarioNaoExistir_AoAtualizarUsuario()
    {
        when(usuarioRepository.findById(usuario.getIdUsuario())).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> usuarioService.update(usuarioRequestDTO, usuario.getIdUsuario()));

        assertEquals("Usuário não encontrado", exception.getMessage());
        verify(usuarioRepository, never()).save(any(Usuario.class));
    }

    @Test
    void deveLancarExcecao_SeUsuarioPerfilNaoExistir_AoAtualizarUsuario()
    {
        usuarioRequestDTO.setIdUsuarioPerfil(999L);
        when(usuarioRepository.findById(usuario.getIdUsuario())).thenReturn(Optional.of(usuario));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> usuarioService.update(usuarioRequestDTO, usuario.getIdUsuario()));

        assertEquals("Perfil de usuário não encontrado", exception.getMessage());
        verify(usuarioRepository, never()).save(any(Usuario.class));
    }

    @Test
    void deveLancarExcecao_SeUsuarioPerfilNaoExistir_AoCriarUsuario()
    {
        usuarioRequestDTO.setIdUsuarioPerfil(999L);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> usuarioService.create(usuarioRequestDTO));

        assertEquals("Perfil de usuário não encontrado", exception.getMessage());
        verify(usuarioRepository, never()).save(any(Usuario.class));
    }

}