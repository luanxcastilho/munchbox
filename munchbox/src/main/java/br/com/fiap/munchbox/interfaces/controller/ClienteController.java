package br.com.fiap.munchbox.interfaces.controller;

import br.com.fiap.munchbox.domain.core.Cliente;
import br.com.fiap.munchbox.domain.core.Usuario;
import br.com.fiap.munchbox.interfaces.dto.ClienteRequestDTO;
import br.com.fiap.munchbox.usecase.cliente.*;
import br.com.fiap.munchbox.usecase.usuario.ConsultarUmUsuarioUseCase;
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
@RequestMapping("/v1/clientes")
@Tag(name = "Clientes", description = "Gerenciamento de clientes")
public class ClienteController {
    private final Logger logger = LoggerFactory.getLogger(ClienteController.class);

    private final CadastrarClienteUseCase cadastrarClienteUseCase;
    private final AtualizarClienteUseCase atualizarClienteUseCase;
    private final RemoverClienteUseCase removerClienteUseCase;
    private final ConsultarTodosClienteUseCase consultarTodosClienteUseCase;
    private final ConsultarUmClienteUseCase consultarUmClienteUseCase;

    private final ConsultarUmUsuarioUseCase consultarUmUsuarioUseCase;

    public ClienteController(CadastrarClienteUseCase cadastrarClienteUseCase,
                             AtualizarClienteUseCase atualizarClienteUseCase,
                             RemoverClienteUseCase removerClienteUseCase,
                             ConsultarTodosClienteUseCase consultarTodosClienteUseCase,
                             ConsultarUmClienteUseCase consultarUmClienteUseCase,
                             ConsultarUmUsuarioUseCase consultarUmUsuarioUseCase) {

        this.cadastrarClienteUseCase = cadastrarClienteUseCase;
        this.atualizarClienteUseCase = atualizarClienteUseCase;
        this.removerClienteUseCase = removerClienteUseCase;
        this.consultarTodosClienteUseCase = consultarTodosClienteUseCase;
        this.consultarUmClienteUseCase = consultarUmClienteUseCase;
        this.consultarUmUsuarioUseCase = consultarUmUsuarioUseCase;
    }


    @PostMapping
    @Operation(summary = "Cadastra um novo cliente")
    public ResponseEntity<Cliente> create(@RequestBody ClienteRequestDTO clienteRequestDTO) {

        logger.info("Iniciando cadastro de cliente: {}", clienteRequestDTO.getNome());

        Usuario usuario = consultarUmUsuarioUseCase.execute(clienteRequestDTO.getIdUsuario())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Cliente cliente = new Cliente();

        cliente.setNome(clienteRequestDTO.getNome());
        cliente.setDataNascimento(clienteRequestDTO.getDataNascimento());
        cliente.setCelular(clienteRequestDTO.getCelular());
        cliente.setEmail(clienteRequestDTO.getEmail());
        cliente.setDataAtualizacao(LocalDateTime.now());
        cliente.setDataInclusao(LocalDateTime.now());
        cliente.setUsuario(usuario);

        cadastrarClienteUseCase.execute(cliente);

        logger.info("Cliente cadastrado com sucesso: {}", cliente.getNome());

        return ResponseEntity.status(201).build();
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza um cliente pelo ID do cliente")
    public ResponseEntity<Cliente> update(@RequestBody ClienteRequestDTO clienteRequestDTO, @PathVariable Long id) {

        logger.info("Iniciando atualização do cliente com ID: {}", id);

        Cliente cliente = consultarUmClienteUseCase.execute(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        Usuario usuario = consultarUmUsuarioUseCase.execute(clienteRequestDTO.getIdUsuario())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        cliente.setNome(clienteRequestDTO.getNome());
        cliente.setDataNascimento(clienteRequestDTO.getDataNascimento());
        cliente.setCelular(clienteRequestDTO.getCelular());
        cliente.setEmail(clienteRequestDTO.getEmail());
        cliente.setDataAtualizacao(LocalDateTime.now());
        cliente.setUsuario(usuario);

        atualizarClienteUseCase.execute(cliente);

        logger.info("Cliente atualizado com sucesso: {}", usuario.getLogin());

        return ResponseEntity.status(200).build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remove um cliente pelo ID do cliente")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {

        logger.info("Iniciando remoção do cliente com ID: {}", id);

        removerClienteUseCase.execute(id);

        logger.info("Cliente removido com sucesso: {}", id);

        return ResponseEntity.ok().build();
    }

    @GetMapping
    @Operation(summary = "Consulta todos os clientes")
    public ResponseEntity<List<Cliente>> findAll(@RequestParam(value = "page", defaultValue = "1") int page,
                                                 @RequestParam(value = "size", defaultValue = "15") int size) {

        logger.info("Iniciando consulta de todos os clientes. Página: {}, Tamanho: {}", page, size);

        Pageable pageable = PageRequest.of(page - 1, size);
        List<Cliente> clientes = consultarTodosClienteUseCase.execute(pageable);

        logger.info("Consulta de todos os clientes concluída. Total de clientes encontrados: {}", clientes.size());

        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Consulta um cliente pelo ID do cliente")
    public ResponseEntity<Optional<Cliente>> findById(@PathVariable Long id) {

        logger.info("Iniciando consulta do cliente com ID: {}", id);

        Optional<Cliente> cliente = consultarUmClienteUseCase.execute(id);

        if (cliente.isPresent()) {
            logger.info("Cliente encontrado: {}", cliente.get().getNome());
        }
        else {
            logger.warn("Cliente com ID {} não encontrado", id);
        }

        return ResponseEntity.ok(cliente);
    }
}
