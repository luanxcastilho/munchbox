package br.com.fiap.munchbox.services;

import br.com.fiap.munchbox.dtos.ProprietarioEnderecoRequestDTO;
import br.com.fiap.munchbox.entities.Proprietario;
import br.com.fiap.munchbox.entities.ProprietarioEndereco;
import br.com.fiap.munchbox.repositories.ProprietarioEnderecoRepository;
import br.com.fiap.munchbox.repositories.ProprietarioRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ProprietarioEnderecoService
{
    private final ProprietarioEnderecoRepository proprietarioEnderecoRepository;
    private final ProprietarioRepository proprietarioRepository;

    public ProprietarioEnderecoService(ProprietarioEnderecoRepository proprietarioEnderecoRepository,
            ProprietarioRepository proprietarioRepository)
    {
        this.proprietarioEnderecoRepository = proprietarioEnderecoRepository;
        this.proprietarioRepository = proprietarioRepository;
    }

    public List<ProprietarioEndereco> findAll(int page,
            int size)
    {
        Pageable pageable = PageRequest.of(
                page - 1,
                size
        );
        return this.proprietarioEnderecoRepository.findAll(pageable).getContent();
    }

    public Optional<ProprietarioEndereco> findById(Long id)
    {
        return this.proprietarioEnderecoRepository.findById(id);
    }

    public void create(ProprietarioEnderecoRequestDTO proprietarioEnderecoRequestDTO)
    {
        Proprietario proprietario = this.proprietarioRepository.findById(proprietarioEnderecoRequestDTO.getIdProprietario()).orElseThrow(() -> new RuntimeException("Proprietário não encontrado"));
        ProprietarioEndereco proprietarioEndereco = new ProprietarioEndereco
                (
                        proprietarioEnderecoRequestDTO.getRua().toUpperCase(),
                        proprietarioEnderecoRequestDTO.getNumero().toUpperCase(),
                        proprietarioEnderecoRequestDTO.getComplemento().toUpperCase(),
                        proprietarioEnderecoRequestDTO.getBairro().toUpperCase(),
                        proprietarioEnderecoRequestDTO.getCidade().toUpperCase(),
                        proprietarioEnderecoRequestDTO.getEstado().toUpperCase(),
                        proprietarioEnderecoRequestDTO.getCep(),
                        LocalDateTime.now(),
                        LocalDateTime.now(),
                        proprietario
                );
        this.proprietarioEnderecoRepository.save(proprietarioEndereco);
    }

    public void update(ProprietarioEnderecoRequestDTO proprietarioEnderecoRequestDTO, Long id)
    {
        ProprietarioEndereco proprietarioEndereco = this.proprietarioEnderecoRepository.findById(id).orElseThrow(() -> new RuntimeException("Endereço do proprietário não encontrado"));
        Proprietario proprietario = this.proprietarioRepository.findById(proprietarioEnderecoRequestDTO.getIdProprietario()).orElseThrow(() -> new RuntimeException("Proprietário não encontrado"));

        proprietarioEndereco.setRua(proprietarioEnderecoRequestDTO.getRua().toUpperCase());
        proprietarioEndereco.setNumero(proprietarioEnderecoRequestDTO.getNumero().toUpperCase());
        proprietarioEndereco.setComplemento(proprietarioEnderecoRequestDTO.getComplemento().toUpperCase());
        proprietarioEndereco.setBairro(proprietarioEnderecoRequestDTO.getBairro().toUpperCase());
        proprietarioEndereco.setCidade(proprietarioEnderecoRequestDTO.getCidade().toUpperCase());
        proprietarioEndereco.setEstado(proprietarioEnderecoRequestDTO.getEstado().toUpperCase());
        proprietarioEndereco.setCep(proprietarioEnderecoRequestDTO.getCep());
        proprietarioEndereco.setDataAtualizacao(LocalDateTime.now());
        proprietarioEndereco.setProprietario(proprietario);

        this.proprietarioEnderecoRepository.save(proprietarioEndereco);
    }

    public void delete(Long id)
    {
        this.proprietarioEnderecoRepository.deleteById(id);
    }
}
