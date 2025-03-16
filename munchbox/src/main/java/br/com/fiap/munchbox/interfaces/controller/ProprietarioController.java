package br.com.fiap.munchbox.interfaces.controller;

import br.com.fiap.munchbox.interfaces.dto.ProprietarioRequestDTO;
import br.com.fiap.munchbox.usecase.proprietario.*;
import br.com.fiap.munchbox.usecase.usuario.ConsultarUmUsuarioUseCase;
import br.com.fiap.munchbox.domain.core.Proprietario;
import br.com.fiap.munchbox.domain.core.Usuario;
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
@RequestMapping("/v1/proprietarios")
@Tag(name = "Proprietários", description = "Gerenciamento de proprietários")
public class ProprietarioController
{
    private final Logger logger = LoggerFactory.getLogger(ProprietarioController.class);

    private final CadastrarProprietarioUseCase cadastrarProprietarioUseCase;
    private final AtualizarProprietarioUseCase atualizarProprietarioUseCase;
    private final RemoverProprietarioUseCase removerProprietarioUseCase;
    private final ConsultarTodosProprietarioUseCase consultarTodosProprietarioUseCase;
    private final ConsultarUmProprietarioUseCase consultarUmProprietarioUseCase;

    private final ConsultarUmUsuarioUseCase consultarUmUsuarioUseCase;

    public ProprietarioController(CadastrarProprietarioUseCase cadastrarProprietarioUseCase, AtualizarProprietarioUseCase atualizarProprietarioUseCase, RemoverProprietarioUseCase removerProprietarioUseCase, ConsultarTodosProprietarioUseCase consultarTodosProprietarioUseCase, ConsultarUmProprietarioUseCase consultarUmProprietarioUseCase, ConsultarUmUsuarioUseCase consultarUmUsuarioUseCase)
    {
        this.cadastrarProprietarioUseCase = cadastrarProprietarioUseCase;
        this.atualizarProprietarioUseCase = atualizarProprietarioUseCase;
        this.removerProprietarioUseCase = removerProprietarioUseCase;
        this.consultarTodosProprietarioUseCase = consultarTodosProprietarioUseCase;
        this.consultarUmProprietarioUseCase = consultarUmProprietarioUseCase;
        this.consultarUmUsuarioUseCase = consultarUmUsuarioUseCase;
    }


    @PostMapping
    @Operation(summary = "Cadastra um novo proprietário")
    public ResponseEntity<Proprietario> create(@RequestBody ProprietarioRequestDTO proprietarioRequestDTO)
    {
        logger.info("Iniciando cadastro de proprietário: {}", proprietarioRequestDTO.getNome());

        Usuario usuario = consultarUmUsuarioUseCase.execute(proprietarioRequestDTO.getIdUsuario()).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        Proprietario proprietario = new Proprietario();

        proprietario.setNome(proprietarioRequestDTO.getNome());
        proprietario.setDataNascimento(proprietarioRequestDTO.getDataNascimento());
        proprietario.setCelular(proprietarioRequestDTO.getCelular());
        proprietario.setEmail(proprietarioRequestDTO.getEmail());
        proprietario.setDataAtualizacao(LocalDateTime.now());
        proprietario.setDataInclusao(LocalDateTime.now());
        proprietario.setUsuario(usuario);

        cadastrarProprietarioUseCase.execute(proprietario);

        logger.info("Proprietário cadastrado com sucesso: {}", proprietario.getNome());

        return ResponseEntity.status(201).build();
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza um proprietário pelo ID do proprietário")
    public ResponseEntity<Proprietario> create(@RequestBody ProprietarioRequestDTO proprietarioRequestDTO, @PathVariable Long id)
    {
        logger.info("Iniciando atualização do proprietário com ID: {}", id);

        Proprietario proprietario = consultarUmProprietarioUseCase.execute(id).orElseThrow(() -> new RuntimeException("Proprietário não encontrado"));
        Usuario usuario = consultarUmUsuarioUseCase.execute(proprietarioRequestDTO.getIdUsuario()).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        proprietario.setNome(proprietarioRequestDTO.getNome());
        proprietario.setDataNascimento(proprietarioRequestDTO.getDataNascimento());
        proprietario.setCelular(proprietarioRequestDTO.getCelular());
        proprietario.setEmail(proprietarioRequestDTO.getEmail());
        proprietario.setDataAtualizacao(LocalDateTime.now());
        proprietario.setUsuario(usuario);

        atualizarProprietarioUseCase.execute(proprietario);

        logger.info("Proprietário atualizado com sucesso: {}", usuario.getLogin());

        return ResponseEntity.status(200).build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remove um proprietário pelo ID do proprietário")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id)
    {
        logger.info("Iniciando remoção do proprietário com ID: {}", id);

        removerProprietarioUseCase.execute(id);

        logger.info("Proprietário removido com sucesso: {}", id);

        return ResponseEntity.ok().build();
    }

    @GetMapping
    @Operation(summary = "Consulta todos os proprietários")
    public ResponseEntity<List<Proprietario>> findAll(@RequestParam(value = "page", defaultValue = "1") int page, @RequestParam(value = "size", defaultValue = "15") int size)
    {
        logger.info("Iniciando consulta de todos os proprietários. Página: {}, Tamanho: {}", page, size);

        Pageable pageable = PageRequest.of(page - 1, size);
        List<Proprietario> proprietarios = consultarTodosProprietarioUseCase.execute(pageable);

        logger.info("Consulta de todos os proprietários concluída. Total de proprietários encontrados: {}", proprietarios.size());

        return ResponseEntity.ok(proprietarios);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Consulta um proprietário pelo ID do proprietário")
    public ResponseEntity<Optional<Proprietario>> findById(@PathVariable Long id)
    {
        logger.info("Iniciando consulta do proprietário com ID: {}", id);

        Optional<Proprietario> proprietario = consultarUmProprietarioUseCase.execute(id);

        if (proprietario.isPresent()) {
            logger.info("Proprietário encontrado: {}", proprietario.get().getNome());
        } else {
            logger.warn("Proprietário com ID {} não encontrado", id);
        }

        return ResponseEntity.ok(proprietario);
    }
}
