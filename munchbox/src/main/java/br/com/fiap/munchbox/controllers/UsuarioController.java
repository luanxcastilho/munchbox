package br.com.fiap.munchbox.controllers;

import br.com.fiap.munchbox.dtos.UsuarioRequestDTO;
import br.com.fiap.munchbox.entities.Usuario;
import br.com.fiap.munchbox.services.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/usuarios")
@Tag(
        name = "Usuários",
        description = "Gerenciamento de usuários"
)
public class UsuarioController
{

    private final Logger logger = LoggerFactory.getLogger(UsuarioController.class);

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService)
    {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    @Operation(
            summary = "Lista todos os usuários"
    )
    public ResponseEntity<List<Usuario>> findAll(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "15") int size
    )
    {
        logger.info("GET /v1/usuarios PAGE: " + page + " SIZE: " + size);
        var usuario = usuarioService.findAll(
                page,
                size
        );
        return ResponseEntity.ok(usuario);
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Busca um usuário pelo ID do usuário"
    )
    public ResponseEntity<Optional<Usuario>> findById(
            @PathVariable("id") Long id
    )
    {
        logger.info("GET /v1/usuarios/" + id);
        var usuario = this.usuarioService.findById(id);
        logger.info("    " + usuario.toString());
        return ResponseEntity.ok(usuario);
    }

    @PostMapping
    @Operation(
            summary = "Cadastra um novo usuário"
    )
    public ResponseEntity<Usuario> create(
            @RequestBody UsuarioRequestDTO usuario
    )
    {
        logger.info("POST /v1/usuarios BODY: " + usuario);
        this.usuarioService.create(usuario);
        return ResponseEntity.status(201).build();
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Atualiza um usuário pelo ID do usuário"
    )
    public ResponseEntity<Usuario> update(
            @PathVariable("id") Long id,
            @RequestBody UsuarioRequestDTO usuario
    )
    {
        logger.info("PUT /v1/usuarios/" + id + " BODY: " + usuario);
        this.usuarioService.update(
                usuario,
                id
        );
        return ResponseEntity.ok().build();
    }


    @DeleteMapping("/{id}")
    @Operation(
            summary = "Remove um usuário pelo ID do usuário"
    )
    public ResponseEntity<Void> delete(
            @PathVariable("id") Long id
    )
    {
        logger.info("DELETE /v1/usuarios/" + id);
        this.usuarioService.delete(id);
        return ResponseEntity.ok().build();
    }
}
