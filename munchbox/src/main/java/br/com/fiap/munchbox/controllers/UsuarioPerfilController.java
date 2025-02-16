package br.com.fiap.munchbox.controllers;

import br.com.fiap.munchbox.dtos.usuarioperfil.UsuarioPerfilRequestDTO;
import br.com.fiap.munchbox.entities.UsuarioPerfil;
import br.com.fiap.munchbox.services.UsuarioPerfilService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/usuarios-perfis")
@Tag(
        name = "Perfis de usuários",
        description = "Gerenciamento de perfis de usuários"
)
public class UsuarioPerfilController
{

    private final Logger logger = LoggerFactory.getLogger(UsuarioPerfilController.class);

    private final UsuarioPerfilService usuarioPerfilService;

    public UsuarioPerfilController(UsuarioPerfilService usuarioPerfilService)
    {
        this.usuarioPerfilService = usuarioPerfilService;
    }

    @GetMapping
    @Operation(
            summary = "Lista todos os perfis de usuários"
    )
    public ResponseEntity<List<UsuarioPerfil>> findAll(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "15") int size
    )
    {
        logger.info("GET /v1/usuarios-perfis PAGE: " + page + " SIZE: " + size);
        var usuario = usuarioPerfilService.findAll(
                page,
                size
        );
        return ResponseEntity.ok(usuario);
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Busca um perfil pelo ID do perfil de usuário"
    )
    public ResponseEntity<Optional<UsuarioPerfil>> findById(
            @PathVariable("id") Long id
    )
    {
        logger.info("GET /v1/usuarios-perfis/" + id);
        var usuario = this.usuarioPerfilService.findById(id);
        logger.info("    " + usuario.toString());
        return ResponseEntity.ok(usuario);
    }

    @PostMapping
    @Operation(
            summary = "Cadastra um novo perfil de usuário"
    )
    public ResponseEntity<UsuarioPerfil> create(
            @RequestBody UsuarioPerfilRequestDTO usuarioPerfilRequestDTO
    )
    {
        logger.info("POST /v1/usuarios-perfis BODY: " + usuarioPerfilRequestDTO);
        this.usuarioPerfilService.create(usuarioPerfilRequestDTO);
        return ResponseEntity.status(201).build();
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Atualiza um perfil de usuário pelo ID do perfil de usuário"
    )
    public ResponseEntity<UsuarioPerfil> update(
            @PathVariable("id") Long id,
            @RequestBody UsuarioPerfilRequestDTO usuarioPerfilRequestDTO
    )
    {
        logger.info("PUT /v1/usuarios-perfis/" + id + " BODY: " + usuarioPerfilRequestDTO);
        this.usuarioPerfilService.update(
                usuarioPerfilRequestDTO,
                id
        );
        return ResponseEntity.ok().build();
    }


    @DeleteMapping("/{id}")
    @Operation(
            summary = "Remove um perfil de usuário pelo ID do perfil de usuário"
    )
    public ResponseEntity<Void> delete(
            @PathVariable("id") Long id
    )
    {
        logger.info("DELETE /v1/usuarios-perfis/" + id);
        this.usuarioPerfilService.delete(id);
        return ResponseEntity.ok().build();
    }
}
