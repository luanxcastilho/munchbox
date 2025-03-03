package br.com.fiap.munchbox.services;

import br.com.fiap.munchbox.dtos.ProprietarioRequestDTO;
import br.com.fiap.munchbox.entities.Proprietario;
import br.com.fiap.munchbox.entities.Usuario;
import br.com.fiap.munchbox.repositories.ProprietarioRepository;
import br.com.fiap.munchbox.repositories.UsuarioRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ProprietarioService
{

    private final ProprietarioRepository proprietarioRepository;
    private final UsuarioRepository usuarioRepository;

    public ProprietarioService(ProprietarioRepository proprietarioRepository, UsuarioRepository usuarioRepository)
    {
        this.proprietarioRepository = proprietarioRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public List<Proprietario> findAll(int page, int size)
    {
        Pageable pageable = PageRequest.of(page-1, size);
        return this.proprietarioRepository.findAll(pageable).getContent();
    }

    public Optional<Proprietario> findById(Long id)
    {
        return this.proprietarioRepository.findById(id);
    }

    public void create(ProprietarioRequestDTO proprietarioRequestDTO)
    {
        Usuario usuario = this.usuarioRepository.findById(proprietarioRequestDTO.getIdUsuario()).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        LocalDateTime dateTimeNow = LocalDateTime.now();

        Proprietario proprietario = new Proprietario
                (
                        usuario,
                        proprietarioRequestDTO.getNome().toUpperCase(),
                        proprietarioRequestDTO.getEmail().toUpperCase(),
                        proprietarioRequestDTO.getCelular(),
                        proprietarioRequestDTO.getDataNascimento(),
                        dateTimeNow,
                        dateTimeNow
                );

        this.proprietarioRepository.save(proprietario);
    }

    public void update(ProprietarioRequestDTO proprietarioRequestDTO, Long id)
    {
        Proprietario proprietario = this.proprietarioRepository.findById(id).orElseThrow((() -> new RuntimeException("Proprietário não encontrado")));
        Usuario usuario = this.usuarioRepository.findById(proprietarioRequestDTO.getIdUsuario()).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));


        proprietario.setNome(proprietarioRequestDTO.getNome().toUpperCase());
        proprietario.setEmail(proprietarioRequestDTO.getEmail().toUpperCase());
        proprietario.setCelular(proprietarioRequestDTO.getCelular());
        proprietario.setDataNascimento(proprietarioRequestDTO.getDataNascimento());
        proprietario.setUsuario(usuario);

        this.proprietarioRepository.save(proprietario);
    }

    public void delete(Long id)
    {
        this.proprietarioRepository.deleteById(id);
    }

}
