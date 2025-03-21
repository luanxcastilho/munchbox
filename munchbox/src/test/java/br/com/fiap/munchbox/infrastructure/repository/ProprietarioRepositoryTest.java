package br.com.fiap.munchbox.infrastructure.repository;

import br.com.fiap.munchbox.domain.core.Proprietario;
import br.com.fiap.munchbox.domain.core.Usuario;
import br.com.fiap.munchbox.domain.core.UsuarioPerfil;
import br.com.fiap.munchbox.infrastructure.entity.ProprietarioEntity;
import br.com.fiap.munchbox.infrastructure.mapper.ProprietarioMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProprietarioRepositoryTest {

    @Mock
    private ProprietarioRepositoryJpa proprietarioRepositoryJpa;

    @InjectMocks
    private ProprietarioRepository proprietarioRepository;

    private Proprietario proprietario;
    private ProprietarioEntity proprietarioEntity;

    @BeforeEach
    void setUp() {
        UsuarioPerfil usuarioPerfil = new UsuarioPerfil(1L, "Administrador", LocalDateTime.now(), LocalDateTime.now());

        Usuario usuario = new Usuario(1L,
                                      "usuario@test.com",
                                      "senha123",
                                      LocalDateTime.now(),
                                      LocalDateTime.now(),
                                      usuarioPerfil);

        proprietario = new Proprietario(1L,
                                        usuario,
                                        "Nome Proprietario",
                                        LocalDate.of(1985, 5, 20),
                                        "11999999999",
                                        "proprietario@test.com",
                                        LocalDateTime.now(),
                                        LocalDateTime.now());

        proprietarioEntity = ProprietarioMapper.toEntity(proprietario);
    }

    @Test
    void deveCadastrarProprietario() {
        proprietarioRepository.create(proprietario);
        verify(proprietarioRepositoryJpa, times(1)).save(any(ProprietarioEntity.class));
    }

    @Test
    void deveAtualizarProprietario() {
        proprietarioRepository.update(proprietario);
        verify(proprietarioRepositoryJpa, times(1)).save(any(ProprietarioEntity.class));
    }

    @Test
    void deveExcluirProprietario() {
        Long id = 1L;
        proprietarioRepository.delete(id);
        verify(proprietarioRepositoryJpa, times(1)).deleteById(id);
    }

    @Test
    void deveRetornarUmProprietario() {
        when(proprietarioRepositoryJpa.findById(1L)).thenReturn(Optional.of(proprietarioEntity));
        Optional<Proprietario> result = proprietarioRepository.findById(1L);
        assertTrue(result.isPresent());
        assertEquals(proprietario.getId(), result.get().getId());
    }

    @Test
    void deveRetornarTodosProprietario() {
        Pageable pageable = PageRequest.of(0, 10);
        List<ProprietarioEntity> entities = List.of(proprietarioEntity);
        Page<ProprietarioEntity> page = new PageImpl<>(entities);

        when(proprietarioRepositoryJpa.findAll(pageable)).thenReturn(page);

        List<Proprietario> result = proprietarioRepository.findAll(pageable);
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
    }
}
