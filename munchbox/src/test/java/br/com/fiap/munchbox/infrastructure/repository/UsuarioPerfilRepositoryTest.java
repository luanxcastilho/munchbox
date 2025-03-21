package br.com.fiap.munchbox.infrastructure.repository;

import br.com.fiap.munchbox.domain.core.UsuarioPerfil;
import br.com.fiap.munchbox.infrastructure.entity.UsuarioPerfilEntity;
import br.com.fiap.munchbox.infrastructure.mapper.UsuarioPerfilMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UsuarioPerfilRepositoryTest {

    @Mock
    private UsuarioPerfilRepositoryJpa usuarioPerfilRepositoryJpa;

    @InjectMocks
    private UsuarioPerfilRepository usuarioPerfilRepository;

    private UsuarioPerfil usuarioPerfil;
    private UsuarioPerfilEntity usuarioPerfilEntity;

    @BeforeEach
    void setUp() {
        usuarioPerfil = new UsuarioPerfil(1L, "Administrador", LocalDateTime.now(), LocalDateTime.now());
        usuarioPerfilEntity = UsuarioPerfilMapper.toEntity(usuarioPerfil);
    }

    @Test
    void deveCriar_QuandoReceberUsuarioPerfil_AoSalvar() {
        when(usuarioPerfilRepositoryJpa.save(any(UsuarioPerfilEntity.class))).thenReturn(usuarioPerfilEntity);
        usuarioPerfilRepository.create(usuarioPerfil);
        verify(usuarioPerfilRepositoryJpa, times(1)).save(any(UsuarioPerfilEntity.class));
    }

    @Test
    void deveAtualizar_QuandoReceberUsuarioPerfil_AoSalvar() {
        when(usuarioPerfilRepositoryJpa.save(any(UsuarioPerfilEntity.class))).thenReturn(usuarioPerfilEntity);
        usuarioPerfilRepository.update(usuarioPerfil);
        verify(usuarioPerfilRepositoryJpa, times(1)).save(any(UsuarioPerfilEntity.class));
    }

    @Test
    void deveDeletar_QuandoReceberId_AoExcluir() {
        doNothing().when(usuarioPerfilRepositoryJpa).deleteById(1L);
        usuarioPerfilRepository.delete(1L);
        verify(usuarioPerfilRepositoryJpa, times(1)).deleteById(1L);
    }

    @Test
    void deveRetornarUsuarioPerfil_QuandoIdExistir_AoBuscarPorId() {
        when(usuarioPerfilRepositoryJpa.findById(1L)).thenReturn(Optional.of(usuarioPerfilEntity));
        Optional<UsuarioPerfil> resultado = usuarioPerfilRepository.findById(1L);
        assertThat(resultado).isPresent();
        assertThat(resultado.get().getId()).isEqualTo(1L);
    }

    @Test
    void deveRetornarVazio_QuandoIdNaoExistir_AoBuscarPorId() {
        when(usuarioPerfilRepositoryJpa.findById(2L)).thenReturn(Optional.empty());
        Optional<UsuarioPerfil> resultado = usuarioPerfilRepository.findById(2L);
        assertThat(resultado).isEmpty();
    }

    @Test
    void deveRetornarListaDeUsuarioPerfil_QuandoExistiremRegistros_AoBuscarTodos() {
        List<UsuarioPerfilEntity> entities = List.of(usuarioPerfilEntity);
        Page<UsuarioPerfilEntity> page = new PageImpl<>(entities);
        when(usuarioPerfilRepositoryJpa.findAll(any(PageRequest.class))).thenReturn(page);
        List<UsuarioPerfil> resultado = usuarioPerfilRepository.findAll(PageRequest.of(0, 10));
        assertThat(resultado).hasSize(1);
        assertThat(resultado.get(0).getId()).isEqualTo(1L);
    }
}
