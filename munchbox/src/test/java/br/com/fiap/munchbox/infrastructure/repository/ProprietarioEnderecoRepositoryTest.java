package br.com.fiap.munchbox.infrastructure.repository;

import br.com.fiap.munchbox.domain.core.Proprietario;
import br.com.fiap.munchbox.domain.core.ProprietarioEndereco;
import br.com.fiap.munchbox.domain.core.Usuario;
import br.com.fiap.munchbox.domain.core.UsuarioPerfil;
import br.com.fiap.munchbox.infrastructure.entity.ProprietarioEnderecoEntity;
import br.com.fiap.munchbox.infrastructure.mapper.ProprietarioEnderecoMapper;
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
class ProprietarioEnderecoRepositoryTest {

    @Mock
    private ProprietarioEnderecoRepositoryJpa proprietarioEnderecoRepositoryJpa;

    @InjectMocks
    private ProprietarioEnderecoRepository proprietarioEnderecoRepository;

    private ProprietarioEndereco proprietarioEndereco;
    private ProprietarioEnderecoEntity proprietarioEnderecoEntity;

    @BeforeEach
    void setUp() {
        UsuarioPerfil usuarioPerfil = new UsuarioPerfil(1L, "Administrador", LocalDateTime.now(), LocalDateTime.now());

        Usuario usuario = new Usuario(1L,
                                      "usuario@test.com",
                                      "senha123",
                                      LocalDateTime.now(),
                                      LocalDateTime.now(),
                                      usuarioPerfil);

        Proprietario proprietario = new Proprietario(1L,
                                                     usuario,
                                                     "Nome Proprietario",
                                                     LocalDate.now(),
                                                     "11999999999",
                                                     "proprietario@test.com",
                                                     LocalDateTime.now(),
                                                     LocalDateTime.now());


        proprietarioEndereco = new ProprietarioEndereco(1L,
                                                        proprietario,
                                                        "Rua Teste",
                                                        "123",
                                                        "Complemento",
                                                        "Bairro",
                                                        "Cidade",
                                                        "Estado",
                                                        "12345-678",
                                                        LocalDateTime.now(),
                                                        LocalDateTime.now());

        proprietarioEnderecoEntity = ProprietarioEnderecoMapper.toEntity(proprietarioEndereco);
    }

    @Test
    void deveCadastrarProprietarioEndereco() {
        proprietarioEnderecoRepository.create(proprietarioEndereco);
        verify(proprietarioEnderecoRepositoryJpa, times(1)).save(any(ProprietarioEnderecoEntity.class));
    }

    @Test
    void deveAtualizarProprietarioEndereco() {
        proprietarioEnderecoRepository.update(proprietarioEndereco);
        verify(proprietarioEnderecoRepositoryJpa, times(1)).save(any(ProprietarioEnderecoEntity.class));
    }

    @Test
    void deveExcluirProprietarioEndereco() {
        Long id = 1L;
        proprietarioEnderecoRepository.delete(id);
        verify(proprietarioEnderecoRepositoryJpa, times(1)).deleteById(id);
    }

    @Test
    void deveRetornarUmProprietarioEndereco() {
        when(proprietarioEnderecoRepositoryJpa.findById(1L)).thenReturn(Optional.of(proprietarioEnderecoEntity));
        Optional<ProprietarioEndereco> result = proprietarioEnderecoRepository.findById(1L);
        assertTrue(result.isPresent());
        assertEquals(proprietarioEndereco.getId(), result.get().getId());
    }

    @Test
    void deveRetornarTodosProprietarioEndereco() {
        Pageable pageable = PageRequest.of(0, 10);
        List<ProprietarioEnderecoEntity> entities = List.of(proprietarioEnderecoEntity);
        Page<ProprietarioEnderecoEntity> page = new PageImpl<>(entities);

        when(proprietarioEnderecoRepositoryJpa.findAll(pageable)).thenReturn(page);

        List<ProprietarioEndereco> result = proprietarioEnderecoRepository.findAll(pageable);
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
    }
}