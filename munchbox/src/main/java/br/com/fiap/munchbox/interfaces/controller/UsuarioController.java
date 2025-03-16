package br.com.fiap.munchbox.interfaces.controller;

import br.com.fiap.munchbox.interfaces.dto.UsuarioRequestDTO;
import br.com.fiap.munchbox.usecase.usuario.*;
import br.com.fiap.munchbox.usecase.usuarioperfil.ConsultarUmUsuarioPerfilUseCase;
import br.com.fiap.munchbox.domain.core.Usuario;
import br.com.fiap.munchbox.domain.core.UsuarioPerfil;
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
@RequestMapping("/v1/usuarios")
@Tag(name = "Usuários", description = "Gerenciamento de usuários")
public class UsuarioController
{
    private final Logger logger = LoggerFactory.getLogger(UsuarioController.class);

    private final ConsultarUmUsuarioPerfilUseCase consultarUmUsuarioPerfilUseCase;
    private final CadastrarUsuarioUseCase cadastrarUsuarioUseCase;
    private final AtualizarUsuarioUseCase atualizarUsuarioUseCase;
    private final RemoverUsuarioUseCase removerUsuarioUseCase;
    private final ConsultarTodosUsuarioUseCase consultarTodosUsuarioUseCase;

    private final ConsultarUmUsuarioUseCase consultarUmUsuarioUseCase;

    public UsuarioController(ConsultarUmUsuarioPerfilUseCase consultarUmUsuarioPerfilUseCase, CadastrarUsuarioUseCase cadastrarUsuarioUseCase, AtualizarUsuarioUseCase atualizarUsuarioUseCase, RemoverUsuarioUseCase removerUsuarioUseCase, ConsultarTodosUsuarioUseCase consultarTodosUsuarioUseCase, ConsultarUmUsuarioUseCase consultarUmUsuarioUseCase)
    {
        this.consultarUmUsuarioPerfilUseCase = consultarUmUsuarioPerfilUseCase;
        this.cadastrarUsuarioUseCase = cadastrarUsuarioUseCase;
        this.atualizarUsuarioUseCase = atualizarUsuarioUseCase;
        this.removerUsuarioUseCase = removerUsuarioUseCase;
        this.consultarTodosUsuarioUseCase = consultarTodosUsuarioUseCase;
        this.consultarUmUsuarioUseCase = consultarUmUsuarioUseCase;
    }


    @PostMapping
    @Operation(summary = "Cadastra um novo usuário")
    public ResponseEntity<Usuario> create(@RequestBody UsuarioRequestDTO usuarioRequestDTO)
    {
        logger.info("Iniciando cadastro de usuário: {}", usuarioRequestDTO.getLogin());

        UsuarioPerfil usuarioPerfil = consultarUmUsuarioPerfilUseCase.execute(usuarioRequestDTO.getIdUsuarioPerfil()).orElseThrow(() -> new RuntimeException("Perfil de usuário não encontrado"));
        Usuario usuario = new Usuario();

        usuario.setLogin(usuarioRequestDTO.getLogin());
        usuario.setSenha(usuarioRequestDTO.getSenha());
        usuario.setDataAtualizacao(LocalDateTime.now());
        usuario.setDataInclusao(LocalDateTime.now());
        usuario.setUsuarioPerfil(usuarioPerfil);

        cadastrarUsuarioUseCase.execute(usuario);

        logger.info("Usuário cadastrado com sucesso: {}", usuario.getLogin());

        return ResponseEntity.status(201).build();
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza um usuário pelo ID do usuário")
    public ResponseEntity<Usuario> update(@RequestBody UsuarioRequestDTO usuarioRequestDTO, @PathVariable Long id)
    {
        logger.info("Iniciando atualização do usuário com ID: {}", id);

        Usuario usuario = consultarUmUsuarioUseCase.execute(id).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        UsuarioPerfil usuarioPerfil = consultarUmUsuarioPerfilUseCase.execute(usuarioRequestDTO.getIdUsuarioPerfil()).orElseThrow(() -> new RuntimeException("Perfil de usuário não encontrado"));

        usuario.setLogin(usuarioRequestDTO.getLogin());
        usuario.setSenha(usuarioRequestDTO.getSenha());
        usuario.setDataAtualizacao(LocalDateTime.now());
        usuario.setUsuarioPerfil(usuarioPerfil);

        atualizarUsuarioUseCase.execute(usuario);

        logger.info("Usuário atualizado com sucesso: {}", usuario.getLogin());

        return ResponseEntity.status(200).build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remove um usuário pelo ID do usuário")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id)
    {
        logger.info("Iniciando remoção do usuário com ID: {}", id);

        removerUsuarioUseCase.execute(id);

        logger.info("Usuário removido com sucesso: {}", id);

        return ResponseEntity.ok().build();
    }

    @GetMapping
    @Operation(summary = "Consulta todos os usuários")
    public ResponseEntity<List<Usuario>> findAll(@RequestParam(value = "page", defaultValue = "1") int page, @RequestParam(value = "size", defaultValue = "15") int size)
    {
        logger.info("Iniciando consulta de todos os usuários. Página: {}, Tamanho: {}", page, size);

        Pageable pageable = PageRequest.of(page - 1, size);
        List<Usuario> usuarios = consultarTodosUsuarioUseCase.execute(pageable);

        logger.info("Consulta de todos os usuários concluída. Total de usuários encontrados: {}", usuarios.size());

        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Consulta um usuário pelo ID do usuário")
    public ResponseEntity<Optional<Usuario>> findById(@PathVariable Long id)
    {
        logger.info("Iniciando consulta do usuário com ID: {}", id);

        Optional<Usuario> usuario = consultarUmUsuarioUseCase.execute(id);

        if (usuario.isPresent()) {
            logger.info("Usuário encontrado: {}", usuario.get().getLogin());
        } else {
            logger.warn("Usuário com ID {} não encontrado", id);
        }

        return ResponseEntity.ok(usuario);
    }
}
