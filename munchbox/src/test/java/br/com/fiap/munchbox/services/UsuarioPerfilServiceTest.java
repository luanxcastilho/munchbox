package br.com.fiap.munchbox.services;

import br.com.fiap.munchbox.dtos.UsuarioPerfilRequestDTO;
import br.com.fiap.munchbox.entities.UsuarioPerfil;
import br.com.fiap.munchbox.helpers.UsuarioPerfilHelper;
import br.com.fiap.munchbox.repositories.UsuarioPerfilRepository;
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

public class UsuarioPerfilServiceTest
{
    @Mock
    private UsuarioPerfilRepository usuarioPerfilRepository;

    @InjectMocks
    private UsuarioPerfilService usuarioPerfilService;

    private UsuarioPerfil usuarioPerfil;
    private UsuarioPerfilRequestDTO usuarioPerfilRequestDTO;

    @BeforeEach
    void setUp()
    {
        MockitoAnnotations.openMocks(this);

        usuarioPerfil = UsuarioPerfilHelper.gerarUsuarioPerfil();

        usuarioPerfilRequestDTO = new UsuarioPerfilRequestDTO();
        usuarioPerfilRequestDTO.setNome(usuarioPerfil.getNome());
    }

    @Test
    void devePermitirConsultarTodosUsuarioPerfil()
    {
        Page<UsuarioPerfil> page = new PageImpl<>(List.of(usuarioPerfil));
        when(usuarioPerfilRepository.findAll(any(PageRequest.class))).thenReturn(page);

        List<UsuarioPerfil> resultado = usuarioPerfilService.findAll(1, 15);

        assertFalse(resultado.isEmpty());
        assertEquals(1, resultado.size());
        assertEquals(usuarioPerfil.getNome(), resultado.getFirst().getNome());

        verify(usuarioPerfilRepository, times(1)).findAll(any(PageRequest.class));
    }

    @Test
    void devePermitirConsultarUmUsuarioPerfil()
    {
        when(usuarioPerfilRepository.findById(usuarioPerfil.getIdUsuarioPerfil())).thenReturn(Optional.of(usuarioPerfil));

        Optional<UsuarioPerfil> resultado = usuarioPerfilService.findById(usuarioPerfil.getIdUsuarioPerfil());

        assertFalse(resultado.isEmpty());
        assertEquals(usuarioPerfil.getNome(), resultado.get().getNome());

        verify(usuarioPerfilRepository, times(1)).findById(usuarioPerfil.getIdUsuarioPerfil());
    }

    @Test
    void devePermitirCriarUsuarioPerfil()
    {
        when(usuarioPerfilRepository.save(any(UsuarioPerfil.class))).thenReturn(usuarioPerfil);

        usuarioPerfilService.create(usuarioPerfilRequestDTO);

        verify(usuarioPerfilRepository, times(1)).save(any(UsuarioPerfil.class));
    }

    @Test
    void devePermitirAtualizarUsuarioPerfil()
    {
        when(usuarioPerfilRepository.findById(usuarioPerfil.getIdUsuarioPerfil())).thenReturn(Optional.of(usuarioPerfil));
        when(usuarioPerfilRepository.save(any(UsuarioPerfil.class))).thenReturn(usuarioPerfil);

        usuarioPerfilService.update(usuarioPerfilRequestDTO, usuarioPerfil.getIdUsuarioPerfil());

        verify(usuarioPerfilRepository, times(1)).findById(usuarioPerfil.getIdUsuarioPerfil());
        verify(usuarioPerfilRepository, times(1)).save(any(UsuarioPerfil.class));
    }

    @Test
    void devePermitirRemoverUsuarioPerfil()
    {
        doNothing().when(usuarioPerfilRepository).deleteById(usuarioPerfil.getIdUsuarioPerfil());

        usuarioPerfilService.delete(usuarioPerfil.getIdUsuarioPerfil());

        verify(usuarioPerfilRepository, times(1)).deleteById(usuarioPerfil.getIdUsuarioPerfil());
    }

    @Test
    void deveLancarExcecao_QuandoReceberUsuarioPerfilInexistente()
    {
        when(usuarioPerfilRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> usuarioPerfilService.update(usuarioPerfilRequestDTO, 1L));

        assertEquals("Perfil de usuário não encontrado", exception.getMessage());
        verify(usuarioPerfilRepository, times(1)).findById(1L);
        verify(usuarioPerfilRepository, never()).save(any(UsuarioPerfil.class));
    }
}
