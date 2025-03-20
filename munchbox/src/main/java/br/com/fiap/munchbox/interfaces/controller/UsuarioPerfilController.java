package br.com.fiap.munchbox.interfaces.controller;

import br.com.fiap.munchbox.interfaces.dto.UsuarioPerfilRequestDTO;
import br.com.fiap.munchbox.domain.core.UsuarioPerfil;
import br.com.fiap.munchbox.usecase.usuarioperfil.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/usuarios-perfis")
@Tag(name = "Perfis de usuários", description = "Gerenciamento de perfis de usuários")
public class UsuarioPerfilController
{

    private final Logger logger = LoggerFactory.getLogger(UsuarioPerfilController.class);

    private final CadastrarUsuarioPerfilUseCase cadastrarUsuarioPerfilUseCase;
    private final AtualizarUsuarioPerfilUseCase atualizarUsuarioPerfilUseCase;
    private final RemoverUsuarioPerfilUseCase removerUsuarioPerfilUseCase;
    private final ConsultarTodosUsuarioPerfilUseCase consultarTodosUsuarioPerfilUseCase;
    private final ConsultarUmUsuarioPerfilUseCase consultarUmUsuarioPerfilUseCase;

    public UsuarioPerfilController(CadastrarUsuarioPerfilUseCase cadastrarUsuarioPerfilUseCase, AtualizarUsuarioPerfilUseCase atualizarUsuarioPerfilUseCase, RemoverUsuarioPerfilUseCase removerUsuarioPerfilUseCase, ConsultarTodosUsuarioPerfilUseCase consultarTodosUsuarioPerfilUseCase, ConsultarUmUsuarioPerfilUseCase consultarUmUsuarioPerfilUseCase)
    {
        this.cadastrarUsuarioPerfilUseCase = cadastrarUsuarioPerfilUseCase;
        this.atualizarUsuarioPerfilUseCase = atualizarUsuarioPerfilUseCase;
        this.removerUsuarioPerfilUseCase = removerUsuarioPerfilUseCase;
        this.consultarTodosUsuarioPerfilUseCase = consultarTodosUsuarioPerfilUseCase;
        this.consultarUmUsuarioPerfilUseCase = consultarUmUsuarioPerfilUseCase;
    }

    @PostMapping
    @Operation(summary = "Cadastra um novo perfil de usuário")
    public ResponseEntity<UsuarioPerfil> create(@RequestBody UsuarioPerfilRequestDTO usuarioPerfilRequestDTO)
    {
        logger.info("Iniciando cadastro de perfil de usuário: {}", usuarioPerfilRequestDTO.getNome());

        UsuarioPerfil usuarioPerfil = new UsuarioPerfil();
        usuarioPerfil.setNome(usuarioPerfilRequestDTO.getNome());
        usuarioPerfil.setDataAtualizacao(LocalDateTime.now());
        usuarioPerfil.setDataInclusao(LocalDateTime.now());

        cadastrarUsuarioPerfilUseCase.execute(usuarioPerfil);

        logger.info("Perfil de usuário cadastrado com sucesso: {}", usuarioPerfil.getNome());

        return ResponseEntity.status(201).build();
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza um perfil de usuário pelo ID do perfil de usuário")
    public ResponseEntity<UsuarioPerfil> update(@RequestBody UsuarioPerfilRequestDTO usuarioPerfilRequestDTO, @PathVariable Long id)
    {
        logger.info("Iniciando atualização do perfil de usuário com ID: {}", id);

        UsuarioPerfil usuarioPerfil = consultarUmUsuarioPerfilUseCase.execute(id).orElseThrow(() -> new RuntimeException("Perfil de usuário não encontrado"));
        usuarioPerfil.setNome(usuarioPerfilRequestDTO.getNome());

        atualizarUsuarioPerfilUseCase.execute(usuarioPerfil);

        logger.info("Perfil de usuário atualizado com sucesso: {}", usuarioPerfil.getNome());

        return ResponseEntity.status(200).build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remove um perfil de usuário pelo ID do perfil de usuário")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id)
    {
        logger.info("Iniciando remoção do perfil de usuário com ID: {}", id);

        removerUsuarioPerfilUseCase.execute(id);

        logger.info("Perfil de usuário removido com sucesso: {}", id);

        return ResponseEntity.ok().build();
    }

    @GetMapping
    @Operation(summary = "Consulta todos os perfis de usuários")
    public ResponseEntity<List<UsuarioPerfil>> findAll(@RequestParam(value = "page", defaultValue = "1") int page, @RequestParam(value = "size", defaultValue = "15") int size)
    {
        logger.info("Iniciando consulta de todos os perfis de usuários. Página: {}, Tamanho: {}", page, size);

        Pageable pageable = PageRequest.of(page - 1, size);
        List<UsuarioPerfil> usuarioPerfis = consultarTodosUsuarioPerfilUseCase.execute(pageable);

        logger.info("Consulta de todos os perfis de usuários concluída. Total de perfis encontrados: {}", usuarioPerfis.size());

        return ResponseEntity.ok(usuarioPerfis);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Consulta um perfil de usuário pelo ID do perfil de usuário")
    public ResponseEntity<Optional<UsuarioPerfil>> findById(@PathVariable Long id)
    {
        logger.info("Iniciando consulta do perfil de usuário com ID: {}", id);

        Optional<UsuarioPerfil> usuarioPerfil = consultarUmUsuarioPerfilUseCase.execute(id);

        if (usuarioPerfil.isPresent()) {
            logger.info("Perfil de usuário encontrado: {}", usuarioPerfil.get().getNome());
        } else {
            logger.warn("Perfil de usuário com ID {} não encontrado", id);
        }

        return ResponseEntity.ok(usuarioPerfil);
    }
}