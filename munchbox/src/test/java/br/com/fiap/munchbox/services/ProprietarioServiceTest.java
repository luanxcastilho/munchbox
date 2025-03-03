package br.com.fiap.munchbox.services;

import br.com.fiap.munchbox.dtos.ProprietarioRequestDTO;
import br.com.fiap.munchbox.entities.Proprietario;
import br.com.fiap.munchbox.entities.Usuario;
import br.com.fiap.munchbox.helpers.ProprietarioHelper;
import br.com.fiap.munchbox.helpers.UsuarioHelper;
import br.com.fiap.munchbox.repositories.ProprietarioRepository;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ProprietarioServiceTest
{
    @Mock
    private ProprietarioRepository proprietarioRepository;

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private ProprietarioService proprietarioService;

    private Proprietario proprietario;
    private ProprietarioRequestDTO proprietarioRequestDTO;
    private Usuario usuario;

    @BeforeEach
    void setUp()
    {
        MockitoAnnotations.openMocks(this);

        proprietario = ProprietarioHelper.gerarProprietario();
        usuario = UsuarioHelper.gerarUsuario();

        proprietarioRequestDTO = new ProprietarioRequestDTO();

        proprietarioRequestDTO.setNome(proprietario.getNome());
        proprietarioRequestDTO.setEmail(proprietario.getEmail());
        proprietarioRequestDTO.setCelular(proprietario.getCelular());
        proprietarioRequestDTO.setDataNascimento(proprietario.getDataNascimento());
        proprietarioRequestDTO.setIdUsuario(usuario.getIdUsuario());
    }

    @Test
    void devePermitirConsultarTodosProprietarios()
    {
        Page<Proprietario> page = new PageImpl<>(List.of(proprietario));
        when(proprietarioRepository.findAll(any(PageRequest.class))).thenReturn(page);

        List<Proprietario> resultado = proprietarioService.findAll(1, 15);

        verify(proprietarioRepository, times(1)).findAll(any(PageRequest.class));
    }

    @Test
    void devePermitirConsultarUmProprietario()
    {
        when(proprietarioRepository.findById(proprietario.getIdProprietario())).thenReturn(Optional.of(proprietario));

        Optional<Proprietario> resultado = proprietarioService.findById(proprietario.getIdProprietario());

        verify(proprietarioRepository, times(1)).findById(any(Long.class));
    }

    @Test
    void devePermitirCriarProprietario()
    {
        when(usuarioRepository.findById(usuario.getIdUsuario())).thenReturn(Optional.of(usuario));
        when(proprietarioRepository.save(any(Proprietario.class))).thenReturn(proprietario);

        proprietarioService.create(proprietarioRequestDTO);

        verify(proprietarioRepository, times(1)).save(any(Proprietario.class));
    }

    @Test
    void devePermitirAtualizarProprietario()
    {
        when(usuarioRepository.findById(usuario.getIdUsuario())).thenReturn(Optional.of(usuario));
        when(proprietarioRepository.findById(proprietario.getIdProprietario())).thenReturn(Optional.of(proprietario));
        when(proprietarioRepository.save(any(Proprietario.class))).thenReturn(proprietario);

        proprietarioService.update(proprietarioRequestDTO, proprietario.getIdProprietario());

        verify(proprietarioRepository, times(1)).save(any(Proprietario.class));
    }

    @Test
    void devePermitirRemoverProprietario()
    {
        doNothing().when(proprietarioRepository).delete(any(Proprietario.class));

        proprietarioService.delete(proprietario.getIdProprietario());

        verify(proprietarioRepository, times(1)).deleteById(proprietario.getIdProprietario());
    }

    @Test
    void deveLancarExcecao_SeUsuarioNaoExistir_AoCriarProprietario()
    {
        proprietarioRequestDTO.setIdUsuario(999L);
        when(proprietarioRepository.findById(proprietario.getIdProprietario())).thenReturn(Optional.of(proprietario));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> proprietarioService.create(proprietarioRequestDTO));

        assertEquals("Usuário não encontrado", exception.getMessage());
        verify(proprietarioRepository, never()).save(any(Proprietario.class));
    }

    @Test
    void deveLancarExcecao_SeProprietarioNaoExistir_AoAtualizarProprietario()
    {
        when(proprietarioRepository.findById(proprietario.getIdProprietario())).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> proprietarioService.update(proprietarioRequestDTO, proprietario.getIdProprietario()));

        assertEquals("Proprietário não encontrado", exception.getMessage());
        verify(proprietarioRepository, never()).save(any(Proprietario.class));
    }

    @Test
    void deveLancarExcecao_SeUsuarioNaoExistir_AoAtualizarProprietario()
    {
        proprietarioRequestDTO.setIdUsuario(999L);
        when(proprietarioRepository.findById(proprietario.getIdProprietario())).thenReturn(Optional.of(proprietario));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> proprietarioService.update(proprietarioRequestDTO, proprietario.getIdProprietario()));

        assertEquals("Usuário não encontrado", exception.getMessage());
        verify(proprietarioRepository, never()).save(any(Proprietario.class));
    }

}
