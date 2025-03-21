package br.com.fiap.munchbox.infrastructure.repository;

import br.com.fiap.munchbox.domain.core.Usuario;
import br.com.fiap.munchbox.domain.core.UsuarioPerfil;
import br.com.fiap.munchbox.infrastructure.entity.UsuarioEntity;
import br.com.fiap.munchbox.infrastructure.mapper.UsuarioMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UsuarioRepositoryTest {

    @Mock
    private UsuarioRepositoryJpa usuarioRepositoryJpa;

    @InjectMocks
    private UsuarioRepository usuarioRepository;

    private Usuario usuario;
    private UsuarioEntity usuarioEntity;

    @BeforeEach
    void setUp() {

        UsuarioPerfil usuarioPerfil = new UsuarioPerfil(1L, "Administrador", LocalDateTime.now(), LocalDateTime.now());

        usuario = new Usuario(1L,
                                      "usuario@test.com",
                                      "senha123",
                                      LocalDateTime.now(),
                                      LocalDateTime.now(),
                                      usuarioPerfil);

        usuarioEntity = UsuarioMapper.toEntity(usuario);
    }

    @Test
    void deveCriarUsuario_QuandoDadosForemValidos_AoSalvar() {
        when(usuarioRepositoryJpa.save(any(UsuarioEntity.class))).thenReturn(usuarioEntity);

        usuarioRepository.create(usuario);

        verify(usuarioRepositoryJpa, times(1)).save(any(UsuarioEntity.class));
    }

    @Test
    void deveAtualizarUsuario_QuandoUsuarioExistir_AoSalvar() {
        when(usuarioRepositoryJpa.save(any(UsuarioEntity.class))).thenReturn(usuarioEntity);

        usuarioRepository.update(usuario);

        verify(usuarioRepositoryJpa, times(1)).save(any(UsuarioEntity.class));
    }

    @Test
    void deveDeletarUsuario_QuandoIdForValido_AoDeletar() {
        doNothing().when(usuarioRepositoryJpa).deleteById(usuario.getId());

        usuarioRepository.delete(usuario.getId());

        verify(usuarioRepositoryJpa, times(1)).deleteById(usuario.getId());
    }

    @Test
    void deveRetornarUsuario_QuandoIdExistir_AoBuscarPorId() {
        when(usuarioRepositoryJpa.findById(usuario.getId())).thenReturn(Optional.of(usuarioEntity));

        Optional<Usuario> resultado = usuarioRepository.findById(usuario.getId());

        assertTrue(resultado.isPresent());
        assertEquals(usuario.getId(), resultado.get().getId());
        verify(usuarioRepositoryJpa, times(1)).findById(usuario.getId());
    }

    @Test
    void deveRetornarListaDeUsuarios_QuandoExistiremUsuarios_AoBuscarTodos() {
        Page<UsuarioEntity> page = new PageImpl<>(List.of(usuarioEntity));
        when(usuarioRepositoryJpa.findAll(any(Pageable.class))).thenReturn(page);

        List<Usuario> usuarios = usuarioRepository.findAll(Pageable.unpaged());

        assertFalse(usuarios.isEmpty());
        assertEquals(1, usuarios.size());
        verify(usuarioRepositoryJpa, times(1)).findAll(any(Pageable.class));
    }
}
