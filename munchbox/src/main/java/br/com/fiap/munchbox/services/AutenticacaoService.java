package br.com.fiap.munchbox.services;

import br.com.fiap.munchbox.dtos.UsuarioRequestDTO;
import br.com.fiap.munchbox.entities.Usuario;
import br.com.fiap.munchbox.repositories.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class AutenticacaoService
{

    private final UsuarioRepository usuarioRepository;

    public AutenticacaoService(UsuarioRepository usuarioRepository)
    {
        this.usuarioRepository = usuarioRepository;
    }

    public void autenticar(UsuarioRequestDTO usuarioRequestDTO)
    {
        Usuario usuario = this.usuarioRepository.findByLogin(usuarioRequestDTO.getLogin());

        if (!usuario.getSenha().equals(usuarioRequestDTO.getSenha()))
        {
            throw new RuntimeException("Senha incorreta");
        }
    }

}
