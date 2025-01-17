package br.com.fiap.munchbox.services;

import br.com.fiap.munchbox.dtos.usuario.UsuarioRequestDTO;
import br.com.fiap.munchbox.entities.Usuario;
import br.com.fiap.munchbox.repositories.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class AutenticacaoService {

    private final UsuarioRepository usuarioRepository;

    public AutenticacaoService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public void autenticar(UsuarioRequestDTO usuarioRequestDTO) {

        Usuario usuario = this.usuarioRepository.findByLogin(usuarioRequestDTO.getLogin())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        if (!usuario.getSenha().equals(usuarioRequestDTO.getSenha())) {
            throw new RuntimeException("Senha incorreta");
        }
    }

}
