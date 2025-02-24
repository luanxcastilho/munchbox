package br.com.fiap.munchbox.services;

import br.com.fiap.munchbox.dtos.usuario.UsuarioRequestDTO;
import br.com.fiap.munchbox.entities.Usuario;
import br.com.fiap.munchbox.helpers.UsuarioHelper;
import br.com.fiap.munchbox.repositories.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class AutenticacaoServiceTest
{
    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private AutenticacaoService autenticacaoService;

    private Usuario usuario;

    @BeforeEach
    void setUp()
    {
        MockitoAnnotations.openMocks(this);

        usuario = UsuarioHelper.gerarUsuario();
    }

    @Test
    void devePermitirAutenticar()
    {
        UsuarioRequestDTO usuarioRequestDTO = new UsuarioRequestDTO(usuario.getLogin(), usuario.getSenha(), 1L);

        when(usuarioRepository.findByLogin(usuario.getLogin())).thenReturn(usuario);

        assertDoesNotThrow(() -> autenticacaoService.autenticar(usuarioRequestDTO));
    }

    @Test
    void deveLancarExcecao_SeSenhaIncorreta_AoAutenticar()
    {
        UsuarioRequestDTO usuarioRequestDTO = new UsuarioRequestDTO(usuario.getLogin(), "senha errada", 1L);
        when(usuarioRepository.findByLogin(usuarioRequestDTO.getLogin())).thenReturn(usuario);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> autenticacaoService.autenticar(usuarioRequestDTO));

        assertEquals("Senha incorreta", exception.getMessage());
    }

    @Test
    void deveLancarExcecao_SeUsuarioNaoExistir_AoAutenticar()
    {
        UsuarioRequestDTO usuarioRequestDTO = new UsuarioRequestDTO(usuario.getLogin(), usuario.getSenha(), 1L);
        when(usuarioRepository.findByLogin("usuario errado")).thenReturn(null);

        NullPointerException exception = assertThrows(NullPointerException.class, () -> autenticacaoService.autenticar(usuarioRequestDTO));

        assertNotNull(exception);
    }
}
