package br.com.fiap.munchbox.services;

import br.com.fiap.munchbox.dtos.usuario.AlterarSenhaResquestDTO;
import br.com.fiap.munchbox.dtos.usuario.UsuarioRequestDTO;
import br.com.fiap.munchbox.entities.Usuario;
import br.com.fiap.munchbox.repositories.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    
    private final UsuarioRepository usuarioRepository;

    private final AutenticacaoService autenticacaoService;
    
    public UsuarioService(UsuarioRepository usuarioRepository, AutenticacaoService autenticacaoService) {
        this.usuarioRepository = usuarioRepository;
        this.autenticacaoService = autenticacaoService;
    }

    public List<Usuario> findAll(int page, int size) {
        int offset = (page - 1) * size;
        return this.usuarioRepository.findAll(size, offset);
    }

    public Optional<Usuario> findById(Long id) {
        return this.usuarioRepository.findById(id);
    }

    public void create(UsuarioRequestDTO usuario) {
        var create = this.usuarioRepository.create(usuario);
        Assert.state(create == 1, "Erro ao cadastrar usuario " + usuario.getLogin());
    }

    public void update(UsuarioRequestDTO usuario, Long id) {
        var update = this.usuarioRepository.update(usuario, id);
        if(update == 0 ) {
            throw  new RuntimeException("Usuario não encontrado");
        }
    }

    public void delete(Long id) {
        var delete = this.usuarioRepository.delete(id);
        if(delete == 0 ) {
            throw  new RuntimeException("Usuario não encontrado");
        }
    }

    public void alterarSenha(AlterarSenhaResquestDTO usuario, Long id) {

        var usuarioRequestDTO = new UsuarioRequestDTO(usuario.getUsuario(), usuario.getSenha());
        this.autenticacaoService.autenticar(usuarioRequestDTO);

        usuarioRequestDTO.setSenha(usuario.getNovaSenha());

        this.usuarioRepository.update(usuarioRequestDTO, id);
    }
}
