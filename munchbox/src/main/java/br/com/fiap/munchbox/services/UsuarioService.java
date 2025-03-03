package br.com.fiap.munchbox.services;

import br.com.fiap.munchbox.dtos.UsuarioRequestDTO;
import br.com.fiap.munchbox.entities.Usuario;
import br.com.fiap.munchbox.entities.UsuarioPerfil;
import br.com.fiap.munchbox.repositories.UsuarioPerfilRepository;
import br.com.fiap.munchbox.repositories.UsuarioRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService
{
    private final UsuarioRepository usuarioRepository;
    private final UsuarioPerfilRepository usuarioPerfilRepository;

    public UsuarioService(UsuarioRepository usuarioRepository, UsuarioPerfilRepository usuarioPerfilRepository)
    {
        this.usuarioRepository = usuarioRepository;
        this.usuarioPerfilRepository = usuarioPerfilRepository;
    }

    public List<Usuario> findAll(int page, int size)
    {
        Pageable pageable = PageRequest.of(page-1, size);
        return this.usuarioRepository.findAll(pageable).getContent();
    }

    public Optional<Usuario> findById(Long id)
    {
        return this.usuarioRepository.findById(id);
    }

    public void create(UsuarioRequestDTO usuarioRequestDTO)
    {
        UsuarioPerfil usuarioPerfil = this.usuarioPerfilRepository.findById(usuarioRequestDTO.getIdUsuarioPerfil()).orElseThrow(() -> new RuntimeException("Perfil de usuário não encontrado"));

        Usuario usuario = new Usuario
                (
                        usuarioRequestDTO.getLogin().toUpperCase(),
                        usuarioRequestDTO.getSenha(),
                        LocalDateTime.now(),
                        LocalDateTime.now(),
                        usuarioPerfil
                );
        this.usuarioRepository.save(usuario);
    }

    public void update(UsuarioRequestDTO usuarioRequestDTO, Long id)
    {
        Usuario usuario = this.usuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        UsuarioPerfil usuarioPerfil = this.usuarioPerfilRepository.findById(usuarioRequestDTO.getIdUsuarioPerfil()).orElseThrow(() -> new RuntimeException("Perfil de usuário não encontrado"));

        usuario.setLogin(usuarioRequestDTO.getLogin().toUpperCase());
        usuario.setSenha(usuarioRequestDTO.getSenha());
        usuario.setDataAtualizacao(LocalDateTime.now());
        usuario.setUsuarioPerfil(usuarioPerfil);

        this.usuarioRepository.save(usuario);
    }

    public void delete(Long id)
    {
        this.usuarioRepository.deleteById(id);
    }

}
