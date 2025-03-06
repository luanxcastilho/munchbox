package br.com.fiap.munchbox.services;

import br.com.fiap.munchbox.dtos.ProprietarioEnderecoRequestDTO;
import br.com.fiap.munchbox.entities.Proprietario;
import br.com.fiap.munchbox.entities.ProprietarioEndereco;
import br.com.fiap.munchbox.helpers.ProprietarioEnderecoHelper;
import br.com.fiap.munchbox.helpers.ProprietarioHelper;
import br.com.fiap.munchbox.repositories.ProprietarioEnderecoRepository;
import br.com.fiap.munchbox.repositories.ProprietarioRepository;
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
import static org.mockito.Mockito.never;

public class ProprietarioEnderecoServiceTest
{
    @Mock
    private ProprietarioEnderecoRepository proprietarioEnderecoRepository;

    @Mock
    private ProprietarioRepository proprietarioRepository;

    @InjectMocks
    private ProprietarioEnderecoService proprietarioEnderecoService;

    ProprietarioEndereco proprietarioEndereco;
    ProprietarioEnderecoRequestDTO proprietarioEnderecoRequestDTO;
    Proprietario proprietario;

    @BeforeEach
    void setUp()
    {
        MockitoAnnotations.openMocks(this);

        proprietarioEndereco = ProprietarioEnderecoHelper.gerarProprietarioEndereco();
        proprietario = ProprietarioHelper.gerarProprietario();

        proprietarioEnderecoRequestDTO = new ProprietarioEnderecoRequestDTO();

        proprietarioEnderecoRequestDTO.setIdProprietario(proprietario.getIdProprietario());
        proprietarioEnderecoRequestDTO.setRua(proprietarioEndereco.getRua());
        proprietarioEnderecoRequestDTO.setNumero(proprietarioEndereco.getNumero());
        proprietarioEnderecoRequestDTO.setComplemento(proprietarioEndereco.getComplemento());
        proprietarioEnderecoRequestDTO.setBairro(proprietarioEndereco.getBairro());
        proprietarioEnderecoRequestDTO.setCidade(proprietarioEndereco.getCidade());
        proprietarioEnderecoRequestDTO.setEstado(proprietarioEndereco.getEstado());
        proprietarioEnderecoRequestDTO.setCep(proprietarioEndereco.getCep());
    }

    @Test
    void devePermitirConsultarTodosProprietarioEndereco()
    {
        Page<ProprietarioEndereco> page = new PageImpl<>(List.of(proprietarioEndereco));
        when(proprietarioEnderecoRepository.findAll(any(PageRequest.class))).thenReturn(page);

        List<ProprietarioEndereco> resultado = proprietarioEnderecoService.findAll(1, 15);

        verify(proprietarioEnderecoRepository, times(1)).findAll(any(PageRequest.class));
    }

    @Test
    void devePermitirConsultarUmProprietarioEndereco()
    {
        when(proprietarioEnderecoRepository.findById(proprietarioEndereco.getIdProprietarioEndereco())).thenReturn(Optional.of(proprietarioEndereco));

        Optional<ProprietarioEndereco> resultado = proprietarioEnderecoService.findById(proprietarioEndereco.getIdProprietarioEndereco());

        verify(proprietarioEnderecoRepository, times(1)).findById(any(Long.class));
    }

    @Test
    void devePermitirCriarProprietarioEndereco()
    {
        when(proprietarioRepository.findById(proprietario.getIdProprietario())).thenReturn(Optional.of(proprietario));
        when(proprietarioEnderecoRepository.save(any(ProprietarioEndereco.class))).thenReturn(proprietarioEndereco);

        proprietarioEnderecoService.create(proprietarioEnderecoRequestDTO);

        verify(proprietarioEnderecoRepository, times(1)).save(any(ProprietarioEndereco.class));
    }

    @Test
    void devePermitirAtualizarProprietarioEndereco()
    {
        when(proprietarioRepository.findById(proprietario.getIdProprietario())).thenReturn(Optional.of(proprietario));
        when(proprietarioEnderecoRepository.findById(proprietarioEndereco.getIdProprietarioEndereco())).thenReturn(Optional.of(proprietarioEndereco));
        when(proprietarioEnderecoRepository.save(any(ProprietarioEndereco.class))).thenReturn(proprietarioEndereco);

        proprietarioEnderecoService.update(proprietarioEnderecoRequestDTO, proprietarioEndereco.getIdProprietarioEndereco());

        verify(proprietarioEnderecoRepository, times(1)).save(any(ProprietarioEndereco.class));
    }

    @Test
    void devePermitirRemoverProprietarioEndereco()
    {
        doNothing().when(proprietarioEnderecoRepository).delete(any(ProprietarioEndereco.class));

        proprietarioEnderecoService.delete(proprietarioEndereco.getIdProprietarioEndereco());

        verify(proprietarioEnderecoRepository, times(1)).deleteById(proprietarioEndereco.getIdProprietarioEndereco());
    }

    @Test
    void deveLancarExcecao_SeProprietarioNaoExistir_AoCriarProprietarioEndereco()
    {
        proprietarioEnderecoRequestDTO.setIdProprietario(999L);
        when(proprietarioEnderecoRepository.findById(proprietarioEndereco.getIdProprietarioEndereco())).thenReturn(Optional.of(proprietarioEndereco));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> proprietarioEnderecoService.create(proprietarioEnderecoRequestDTO));

        assertEquals("Proprietário não encontrado", exception.getMessage());
        verify(proprietarioEnderecoRepository, never()).save(any(ProprietarioEndereco.class));
    }

    @Test
    void deveLancarExcecao_SeProprietarioEnderecoNaoExistir_AoAtualizarProprietarioEndereco()
    {
        when(proprietarioEnderecoRepository.findById(proprietarioEndereco.getIdProprietarioEndereco())).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> proprietarioEnderecoService.update(proprietarioEnderecoRequestDTO, proprietarioEndereco.getIdProprietarioEndereco()));

        assertEquals("Endereço do proprietário não encontrado", exception.getMessage());
        verify(proprietarioEnderecoRepository, never()).save(any(ProprietarioEndereco.class));
    }

    @Test
    void deveLancarExcecao_SeProprietarioNaoExistir_AoAtualizarProprietarioEndereco()
    {
        proprietarioEnderecoRequestDTO.setIdProprietario(999L);
        when(proprietarioEnderecoRepository.findById(proprietarioEndereco.getIdProprietarioEndereco())).thenReturn(Optional.of(proprietarioEndereco));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> proprietarioEnderecoService.update(proprietarioEnderecoRequestDTO, proprietarioEndereco.getIdProprietarioEndereco()));

        assertEquals("Proprietário não encontrado", exception.getMessage());
        verify(proprietarioEnderecoRepository, never()).save(any(ProprietarioEndereco.class));
    }
}
