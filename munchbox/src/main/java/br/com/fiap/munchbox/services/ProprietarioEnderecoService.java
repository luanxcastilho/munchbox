package br.com.fiap.munchbox.services;

import br.com.fiap.munchbox.dtos.proprietarioendereco.ProprietarioEnderecoRequestDTO;
import br.com.fiap.munchbox.entities.ProprietarioEndereco;
import br.com.fiap.munchbox.repositories.ProprietarioEnderecoRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Service
public class ProprietarioEnderecoService {

    private final ProprietarioEnderecoRepository proprietarioEnderecoRepository;

    public ProprietarioEnderecoService(ProprietarioEnderecoRepository proprietarioEnderecoRepository) {
        this.proprietarioEnderecoRepository = proprietarioEnderecoRepository;
    }

    public List<ProprietarioEndereco> findAll(int page, int size) {
        int offset = (page - 1) * size;
        return this.proprietarioEnderecoRepository.findAll(size, offset);
    }

    public Optional<ProprietarioEndereco> findById(Long id) {
        return this.proprietarioEnderecoRepository.findById(id);
    }

    public void create(ProprietarioEnderecoRequestDTO proprietario) {
        var create = this.proprietarioEnderecoRepository.create(proprietario);
        Assert.state(create == 1, "Erro ao cadastrar endereço " + proprietario.getIdProprietario());
    }

    public void update(ProprietarioEnderecoRequestDTO proprietario, Long id) {
        var update = this.proprietarioEnderecoRepository.update(proprietario, id);
        if (update == 0) {
            throw new RuntimeException("Endereço não encontrado");
        }
    }

    public void delete(Long id) {
        var delete = this.proprietarioEnderecoRepository.delete(id);
        if (delete == 0) {
            throw new RuntimeException("Endereço não encontrado");
        }
    }
}
