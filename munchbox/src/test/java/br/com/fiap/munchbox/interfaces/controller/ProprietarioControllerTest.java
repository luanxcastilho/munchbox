package br.com.fiap.munchbox.interfaces.controller;

import br.com.fiap.munchbox.domain.core.Proprietario;
import br.com.fiap.munchbox.domain.core.Usuario;
import br.com.fiap.munchbox.interfaces.dto.ProprietarioRequestDTO;
import br.com.fiap.munchbox.usecase.proprietario.*;
import br.com.fiap.munchbox.usecase.usuario.ConsultarUmUsuarioUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProprietarioControllerTest {

    @InjectMocks
    private ProprietarioController proprietarioController;

    @Mock
    private CadastrarProprietarioUseCase cadastrarProprietarioUseCase;

    @Mock
    private AtualizarProprietarioUseCase atualizarProprietarioUseCase;

    @Mock
    private RemoverProprietarioUseCase removerProprietarioUseCase;

    @Mock
    private ConsultarTodosProprietarioUseCase consultarTodosProprietarioUseCase;

    @Mock
    private ConsultarUmProprietarioUseCase consultarUmProprietarioUseCase;

    @Mock
    private ConsultarUmUsuarioUseCase consultarUmUsuarioUseCase;

    private Proprietario proprietario;
    private Usuario usuario;

    @BeforeEach
    void setUp() {
        usuario = new Usuario();
        usuario.setId(1L);

        proprietario = new Proprietario();
        proprietario.setId(1L);
        proprietario.setNome("João Silva");
        proprietario.setDataNascimento(LocalDate.of(1990, 1, 1));
        proprietario.setCelular("11999999999");
        proprietario.setEmail("joao@email.com");
        proprietario.setDataInclusao(LocalDateTime.now());
        proprietario.setDataAtualizacao(LocalDateTime.now());
        proprietario.setUsuario(usuario);
    }

    @Test
    void deveCadastrarProprietario() {

        ProprietarioRequestDTO dto = new ProprietarioRequestDTO(1L,
                                                                "João Silva",
                                                                LocalDate.of(1990, 1, 1),
                                                                "11999999999",
                                                                "joao@email.com");

        when(consultarUmUsuarioUseCase.execute(1L)).thenReturn(Optional.of(usuario));

        ResponseEntity<Proprietario> response = proprietarioController.create(dto);

        assertEquals(201, response.getStatusCodeValue());
        verify(cadastrarProprietarioUseCase, times(1)).execute(any(Proprietario.class));
    }

    @Test
    void deveLancarExcecao_QuandoUsuarioNaoEncontrado_AoCadastrarProprietario() {

        ProprietarioRequestDTO dto = new ProprietarioRequestDTO(1L,
                                                                "João Silva",
                                                                LocalDate.of(1990, 1, 1),
                                                                "11999999999",
                                                                "joao@email.com");

        when(consultarUmUsuarioUseCase.execute(anyLong())).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            proprietarioController.create(dto);
        });

        assertEquals("Usuário não encontrado", exception.getMessage());
        verify(consultarUmUsuarioUseCase, times(1)).execute(anyLong());
        verify(cadastrarProprietarioUseCase, never()).execute(any(Proprietario.class));
    }


    @Test
    void deveAtualizarProprietario() {

        ProprietarioRequestDTO dto = new ProprietarioRequestDTO(1L,
                                                                "João Silva",
                                                                LocalDate.of(1990, 1, 1),
                                                                "11999999999",
                                                                "joao@email.com");

        when(consultarUmProprietarioUseCase.execute(1L)).thenReturn(Optional.of(proprietario));
        when(consultarUmUsuarioUseCase.execute(1L)).thenReturn(Optional.of(usuario));

        ResponseEntity<Proprietario> response = proprietarioController.update(dto, 1L);

        assertEquals(200, response.getStatusCodeValue());
        verify(atualizarProprietarioUseCase, times(1)).execute(any(Proprietario.class));
    }

    @Test
    void deveLancarExcecao_QuandoUsuarioNaoEncontrado_AoAtualizarProprietario() {

        ProprietarioRequestDTO dto = new ProprietarioRequestDTO(1L,
                                                                "João Silva",
                                                                LocalDate.of(1990, 1, 1),
                                                                "11999999999",
                                                                "joao@email.com");

        when(consultarUmProprietarioUseCase.execute(anyLong())).thenReturn(Optional.of(proprietario));
        when(consultarUmUsuarioUseCase.execute(anyLong())).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            proprietarioController.update(dto, 1L);
        });

        assertEquals("Usuário não encontrado", exception.getMessage());
        verify(consultarUmUsuarioUseCase, times(1)).execute(anyLong());
        verify(atualizarProprietarioUseCase, never()).execute(any(Proprietario.class));
    }

    @Test
    void deveLancarExcecao_QuandoProprietarioNaoEncontrado_AoAtualizarProprietario() {

        ProprietarioRequestDTO dto = new ProprietarioRequestDTO(1L,
                                                                "João Silva",
                                                                LocalDate.of(1990, 1, 1),
                                                                "11999999999",
                                                                "joao@email.com");

        when(consultarUmProprietarioUseCase.execute(anyLong())).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            proprietarioController.update(dto, 1L);
        });

        assertEquals("Proprietário não encontrado", exception.getMessage());
        verify(consultarUmProprietarioUseCase, times(1)).execute(anyLong());
        verify(atualizarProprietarioUseCase, never()).execute(any(Proprietario.class));
    }

    @Test
    void deveRemoverProprietario() {
        doNothing().when(removerProprietarioUseCase).execute(1L);

        ResponseEntity<Void> response = proprietarioController.delete(1L);

        assertEquals(200, response.getStatusCodeValue());
        verify(removerProprietarioUseCase, times(1)).execute(1L);
    }

    @Test
    void deveConsultarTodosProprietarios() {
        Pageable pageable = PageRequest.of(0, 15);
        when(consultarTodosProprietarioUseCase.execute(pageable)).thenReturn(List.of(proprietario));

        ResponseEntity<List<Proprietario>> response = proprietarioController.findAll(1, 15);

        assertEquals(200, response.getStatusCodeValue());
        assertFalse(response.getBody().isEmpty());
        verify(consultarTodosProprietarioUseCase, times(1)).execute(pageable);
    }

    @Test
    void deveConsultarUmProprietario() {
        when(consultarUmProprietarioUseCase.execute(1L)).thenReturn(Optional.of(proprietario));

        ResponseEntity<Optional<Proprietario>> response = proprietarioController.findById(1L);

        assertEquals(200, response.getStatusCodeValue());
        assertTrue(response.getBody().isPresent());
        verify(consultarUmProprietarioUseCase, times(1)).execute(1L);
    }

    @Test
    void deveGravarLog_QuandoProprietarioNaoEncontrado_AoConsultarUmProprietario() {
        when(consultarUmProprietarioUseCase.execute(1L)).thenReturn(Optional.empty());

        ResponseEntity<Optional<Proprietario>> response = proprietarioController.findById(1L);

        assertEquals(200, response.getStatusCodeValue());
        assertFalse(response.getBody().isPresent());
        verify(consultarUmProprietarioUseCase, times(1)).execute(1L);
    }

}
