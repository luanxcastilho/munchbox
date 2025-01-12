package br.com.fiap.munchbox.services;

import br.com.fiap.munchbox.dtos.proprietario.ProprietarioRequestDTO;
import br.com.fiap.munchbox.entities.Proprietario;
import br.com.fiap.munchbox.repositories.ProprietarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Service
public class ProprietarioService {

    private final ProprietarioRepository proprietarioRepository;

    public ProprietarioService(ProprietarioRepository proprietarioRepository) {
        this.proprietarioRepository = proprietarioRepository;
    }

    public List<Proprietario> findAll(int page, int size) {
        int offset = (page - 1) * size;
        return this.proprietarioRepository.findAll(size, offset);
    }

    public Optional<Proprietario> findById(Long id) {
        return this.proprietarioRepository.findById(id);
    }

    public void create(ProprietarioRequestDTO proprietario) {
        var create = this.proprietarioRepository.create(proprietario);
        Assert.state(create == 1, "Erro ao cadastrar proprietario " + proprietario.getNome());
    }

    public void update(ProprietarioRequestDTO proprietario, Long id) {
        var update = this.proprietarioRepository.update(proprietario, id);
        if(update == 0 ) {
            throw  new RuntimeException("Proprietario não encontrado");
        }
    }

    public void delete(Long id) {
        var delete = this.proprietarioRepository.delete(id);
        if(delete == 0 ) {
            throw  new RuntimeException("Proprietario não encontrado");
        }
    }
    
}
