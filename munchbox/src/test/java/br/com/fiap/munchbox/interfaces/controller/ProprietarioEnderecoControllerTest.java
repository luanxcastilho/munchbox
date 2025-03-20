package br.com.fiap.munchbox.interfaces.controller;

import br.com.fiap.munchbox.domain.core.Cliente;
import br.com.fiap.munchbox.domain.core.Proprietario;
import br.com.fiap.munchbox.domain.core.ProprietarioEndereco;
import br.com.fiap.munchbox.interfaces.dto.ProprietarioEnderecoRequestDTO;
import br.com.fiap.munchbox.usecase.proprietario.ConsultarUmProprietarioUseCase;
import br.com.fiap.munchbox.usecase.proprietarioendereco.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProprietarioEnderecoControllerTest {

    @InjectMocks
    private ProprietarioEnderecoController proprietarioEnderecoController;

    @Mock
    private CadastrarProprietarioEnderecoUseCase cadastrarProprietarioEnderecoUseCase;
    @Mock
    private AtualizarProprietarioEnderecoUseCase atualizarProprietarioEnderecoUseCase;
    @Mock
    private RemoverProprietarioEnderecoUseCase removerProprietarioEnderecoUseCase;
    @Mock
    private ConsultarTodosProprietarioEnderecoUseCase consultarTodosProprietarioEnderecoUseCase;
    @Mock
    private ConsultarUmProprietarioEnderecoUseCase consultarUmProprietarioEnderecoUseCase;
    @Mock
    private ConsultarUmProprietarioUseCase consultarUmProprietarioUseCase;

    private ProprietarioEndereco proprietarioEndereco;
    private Proprietario proprietario;

    @BeforeEach
    void setUp() {
        proprietario = new Proprietario();
        proprietario.setId(1L);

        proprietarioEndereco = new ProprietarioEndereco();
        proprietarioEndereco.setId(1L);
        proprietarioEndereco.setProprietario(proprietario);
        proprietarioEndereco.setRua("Rua Teste");
        proprietarioEndereco.setNumero("123");
        proprietarioEndereco.setCidade("Cidade Teste");
        proprietarioEndereco.setEstado("Estado Teste");
        proprietarioEndereco.setCep("00000-000");
        proprietarioEndereco.setDataInclusao(LocalDateTime.now());
        proprietarioEndereco.setDataAtualizacao(LocalDateTime.now());
    }

    @Test
    void deveCadastrarProprietarioEndereco() {
        ProprietarioEnderecoRequestDTO dto = new ProprietarioEnderecoRequestDTO(1L,
                                                                                "Rua Teste",
                                                                                "123",
                                                                                "Apto 10",
                                                                                "Bairro Teste",
                                                                                "Cidade Teste",
                                                                                "Estado Teste",
                                                                                "00000-000");

        when(consultarUmProprietarioUseCase.execute(anyLong())).thenReturn(Optional.of(proprietario));
        doNothing().when(cadastrarProprietarioEnderecoUseCase).execute(any(ProprietarioEndereco.class));

        ResponseEntity<ProprietarioEndereco> response = proprietarioEnderecoController.create(dto);

        assertEquals(201, response.getStatusCodeValue());
        verify(cadastrarProprietarioEnderecoUseCase, times(1)).execute(any(ProprietarioEndereco.class));
    }

    @Test
    void deveLancarExcecao_QuandoProprietarioNaoEncontrado_AoCadastrarProprietarioEndereco() {

        ProprietarioEnderecoRequestDTO dto = new ProprietarioEnderecoRequestDTO(1L,
                                                                                "Rua Teste",
                                                                                "123",
                                                                                "Apto 10",
                                                                                "Bairro Teste",
                                                                                "Cidade Teste",
                                                                                "Estado Teste",
                                                                                "00000-000");

        when(consultarUmProprietarioUseCase.execute(anyLong())).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            proprietarioEnderecoController.create(dto);
        });

        assertEquals("Proprietário não encontrado", exception.getMessage());
        verify(consultarUmProprietarioUseCase, times(1)).execute(anyLong());
        verify(cadastrarProprietarioEnderecoUseCase, never()).execute(any(ProprietarioEndereco.class));
    }


    @Test
    void deveAtualizarProprietarioEndereco() {
        ProprietarioEnderecoRequestDTO dto = new ProprietarioEnderecoRequestDTO(1L,
                                                                                "Rua Nova",
                                                                                "456",
                                                                                "Casa",
                                                                                "Bairro Novo",
                                                                                "Cidade Nova",
                                                                                "Estado Novo",
                                                                                "11111-111");

        when(consultarUmProprietarioEnderecoUseCase.execute(anyLong())).thenReturn(Optional.of(proprietarioEndereco));
        when(consultarUmProprietarioUseCase.execute(anyLong())).thenReturn(Optional.of(proprietario));
        doNothing().when(atualizarProprietarioEnderecoUseCase).execute(any(ProprietarioEndereco.class));

        ResponseEntity<ProprietarioEndereco> response = proprietarioEnderecoController.update(dto, 1L);

        assertEquals(200, response.getStatusCodeValue());
        verify(atualizarProprietarioEnderecoUseCase, times(1)).execute(any(ProprietarioEndereco.class));
    }

    @Test
    void deveLancarExcecao_QuandoProprietarioNaoEncontrado_AoAtualizarProprietarioEndereco() {

        ProprietarioEnderecoRequestDTO dto = new ProprietarioEnderecoRequestDTO(1L,
                                                                                "Rua Teste",
                                                                                "123",
                                                                                "Apto 10",
                                                                                "Bairro Teste",
                                                                                "Cidade Teste",
                                                                                "Estado Teste",
                                                                                "00000-000");

        when(consultarUmProprietarioEnderecoUseCase.execute(anyLong())).thenReturn(Optional.of(proprietarioEndereco));
        when(consultarUmProprietarioUseCase.execute(anyLong())).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            proprietarioEnderecoController.update(dto, 1L);
        });

        assertEquals("Proprietário não encontrado", exception.getMessage());
        verify(consultarUmProprietarioUseCase, times(1)).execute(anyLong());
        verify(atualizarProprietarioEnderecoUseCase, never()).execute(any(ProprietarioEndereco.class));
    }

    @Test
    void deveLancarExcecao_QuandoProprietarioEnderecoNaoEncontrado_AoAtualizarProprietarioEndereco() {

        ProprietarioEnderecoRequestDTO dto = new ProprietarioEnderecoRequestDTO(1L,
                                                                                "Rua Teste",
                                                                                "123",
                                                                                "Apto 10",
                                                                                "Bairro Teste",
                                                                                "Cidade Teste",
                                                                                "Estado Teste",
                                                                                "00000-000");

        when(consultarUmProprietarioEnderecoUseCase.execute(anyLong())).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            proprietarioEnderecoController.update(dto, 1L);
        });

        assertEquals("Endereço de proprietário não encontrado", exception.getMessage());
        verify(consultarUmProprietarioEnderecoUseCase, times(1)).execute(anyLong());
        verify(atualizarProprietarioEnderecoUseCase, never()).execute(any(ProprietarioEndereco.class));
    }

    @Test
    void deveRemoverProprietarioEndereco() {
        doNothing().when(removerProprietarioEnderecoUseCase).execute(anyLong());

        ResponseEntity<Void> response = proprietarioEnderecoController.delete(1L);

        assertEquals(200, response.getStatusCodeValue());
        verify(removerProprietarioEnderecoUseCase, times(1)).execute(anyLong());
    }

    @Test
    void deveConsultarUmProprietarioEndereco() {

        when(consultarUmProprietarioEnderecoUseCase.execute(anyLong())).thenReturn(Optional.of(proprietarioEndereco));

        ResponseEntity<Optional<ProprietarioEndereco>> response = proprietarioEnderecoController.findById(1L);

        assertEquals(200, response.getStatusCodeValue());
        assertTrue(response.getBody().isPresent());
    }

    @Test
    void deveGravarLog_QuandoProprietarioNaoEncontrado_AoConsultarUmProprietarioEndereco() {

        when(consultarUmProprietarioEnderecoUseCase.execute(anyLong())).thenReturn(Optional.empty());

        ResponseEntity<Optional<ProprietarioEndereco>> response = proprietarioEnderecoController.findById(1L);

        assertEquals(200, response.getStatusCodeValue());
        assertFalse(response.getBody().isPresent());
    }

    @Test
    void deveConsultarTodosProprietarioEnderecos() {
        when(consultarTodosProprietarioEnderecoUseCase.execute(any())).thenReturn(List.of(proprietarioEndereco));

        ResponseEntity<List<ProprietarioEndereco>> response = proprietarioEnderecoController.findAll(1, 10);

        assertEquals(200, response.getStatusCodeValue());
        assertFalse(response.getBody().isEmpty());
    }
}