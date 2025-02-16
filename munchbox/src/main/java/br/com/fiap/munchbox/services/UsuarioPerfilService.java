package br.com.fiap.munchbox.services;

import br.com.fiap.munchbox.dtos.usuario.UsuarioRequestDTO;
import br.com.fiap.munchbox.dtos.usuarioperfil.UsuarioPerfilRequestDTO;
import br.com.fiap.munchbox.entities.UsuarioPerfil;
import br.com.fiap.munchbox.entities.UsuarioPerfil;
import br.com.fiap.munchbox.repositories.UsuarioPerfilRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioPerfilService
{
    private final UsuarioPerfilRepository usuarioPerfilRepository;

    public UsuarioPerfilService(UsuarioPerfilRepository usuarioPerfilRepository)
    {
        this.usuarioPerfilRepository = usuarioPerfilRepository;
    }

    public List<UsuarioPerfil> findAll(int page, int size)
    {
        Pageable pageable = PageRequest.of(page-1, size);
        return this.usuarioPerfilRepository.findAll(pageable).getContent();
    }

    public Optional<UsuarioPerfil> findById(Long id)
    {
        return this.usuarioPerfilRepository.findById(id);
    }

    public void create(UsuarioPerfilRequestDTO usuarioPerfilRequestDTO)
    {
        UsuarioPerfil usuarioPerfil = new UsuarioPerfil
                (
                        usuarioPerfilRequestDTO.getNome(),
                        LocalDateTime.now(),
                        LocalDateTime.now()
                );
        this.usuarioPerfilRepository.save(usuarioPerfil);
    }

    public void update(UsuarioPerfilRequestDTO usuarioPerfilRequestDTO, Long id)
    {
        UsuarioPerfil usuarioPerfil = this.usuarioPerfilRepository.findById(id).orElseThrow(() -> new RuntimeException("Perfil de usuário não encontrado"));

        usuarioPerfil.setNome(usuarioPerfilRequestDTO.getNome());
        usuarioPerfil.setDataAtualizacao(LocalDateTime.now());

        this.usuarioPerfilRepository.save(usuarioPerfil);
    }

    public void delete(Long id)
    {
        this.usuarioPerfilRepository.deleteById(id);
    }
}
