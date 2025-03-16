package br.com.fiap.munchbox.interfaces.controller;

import br.com.fiap.munchbox.interfaces.dto.ClienteEnderecoRequestDTO;
import br.com.fiap.munchbox.usecase.cliente.ConsultarUmClienteUseCase;
import br.com.fiap.munchbox.domain.core.Cliente;
import br.com.fiap.munchbox.domain.core.ClienteEndereco;
import br.com.fiap.munchbox.usecase.clienteendereco.*;
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
@RequestMapping("/v1/clientes-enderecos")
@Tag(name = "Endereços de clientes", description = "Gerenciamento de endereços de clientes")
public class ClienteEnderecoController
{
    private final Logger logger = LoggerFactory.getLogger(ClienteEnderecoController.class);

    private final CadastrarClienteEnderecoUseCase cadastrarClienteEnderecoUseCase;
    private final AtualizarClienteEnderecoUseCase atualizarClienteEnderecoUseCase;
    private final RemoverClienteEnderecoUseCase removerClienteEnderecoUseCase;
    private final ConsultarTodosClienteEnderecoUseCase consultarTodosClienteEnderecoUseCase;
    private final ConsultarUmClienteEnderecoUseCase consultarUmClienteEnderecoUseCase;
    private final ConsultarUmClienteUseCase consultarUmClienteUseCase;

    public ClienteEnderecoController(CadastrarClienteEnderecoUseCase cadastrarClienteEnderecoUseCase, AtualizarClienteEnderecoUseCase atualizarClienteEnderecoUseCase, RemoverClienteEnderecoUseCase removerClienteEnderecoUseCase, ConsultarTodosClienteEnderecoUseCase consultarTodosClienteEnderecoUseCase, ConsultarUmClienteEnderecoUseCase consultarUmClienteEnderecoUseCase, ConsultarUmClienteUseCase consultarUmClienteUseCase)
    {
        this.cadastrarClienteEnderecoUseCase = cadastrarClienteEnderecoUseCase;
        this.atualizarClienteEnderecoUseCase = atualizarClienteEnderecoUseCase;
        this.removerClienteEnderecoUseCase = removerClienteEnderecoUseCase;
        this.consultarTodosClienteEnderecoUseCase = consultarTodosClienteEnderecoUseCase;
        this.consultarUmClienteEnderecoUseCase = consultarUmClienteEnderecoUseCase;
        this.consultarUmClienteUseCase = consultarUmClienteUseCase;
    }


    @PostMapping
    @Operation(summary = "Cadastra um novo endereço de cliente")
    public ResponseEntity<ClienteEndereco> create(@RequestBody ClienteEnderecoRequestDTO clienteEnderecoRequestDTO)
    {
        logger.info("Iniciando cadastro de endereço do cliente: {}", clienteEnderecoRequestDTO.getIdCliente());

        Cliente cliente = consultarUmClienteUseCase.execute(clienteEnderecoRequestDTO.getIdCliente()).orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        ClienteEndereco clienteEndereco = new ClienteEndereco();

        clienteEndereco.setCliente(cliente);
        clienteEndereco.setRua( clienteEnderecoRequestDTO.getRua() );
        clienteEndereco.setNumero( clienteEnderecoRequestDTO.getNumero() );
        clienteEndereco.setComplemento( clienteEnderecoRequestDTO.getComplemento() );
        clienteEndereco.setBairro( clienteEnderecoRequestDTO.getBairro() );
        clienteEndereco.setCidade( clienteEnderecoRequestDTO.getCidade() );
        clienteEndereco.setEstado( clienteEnderecoRequestDTO.getEstado() );
        clienteEndereco.setCep( clienteEnderecoRequestDTO.getCep() );
        clienteEndereco.setDataAtualizacao( LocalDateTime.now() );
        clienteEndereco.setDataInclusao( LocalDateTime.now() );

        cadastrarClienteEnderecoUseCase.execute(clienteEndereco);

        logger.info("Endereço de cliente cadastrado com sucesso: {}", cliente.getId());

        return ResponseEntity.status(201).build();
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza um endereço de cliente pelo ID do endereço de cliente")
    public ResponseEntity<ClienteEndereco> create(@RequestBody ClienteEnderecoRequestDTO clienteEnderecoRequestDTO, @PathVariable Long id)
    {
        logger.info("Iniciando atualização do endereço de cliente com ID: {}", id);

        ClienteEndereco clienteEndereco = consultarUmClienteEnderecoUseCase.execute(id).orElseThrow(() -> new RuntimeException("Endereço de cliente não encontrado"));
        Cliente cliente = consultarUmClienteUseCase.execute(clienteEnderecoRequestDTO.getIdCliente()).orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        clienteEndereco.setCliente(cliente);
        clienteEndereco.setRua( clienteEnderecoRequestDTO.getRua() );
        clienteEndereco.setNumero( clienteEnderecoRequestDTO.getNumero() );
        clienteEndereco.setComplemento( clienteEnderecoRequestDTO.getComplemento() );
        clienteEndereco.setBairro( clienteEnderecoRequestDTO.getBairro() );
        clienteEndereco.setCidade( clienteEnderecoRequestDTO.getCidade() );
        clienteEndereco.setEstado( clienteEnderecoRequestDTO.getEstado() );
        clienteEndereco.setCep( clienteEnderecoRequestDTO.getCep() );
        clienteEndereco.setDataAtualizacao( LocalDateTime.now() );
        clienteEndereco.setDataInclusao( LocalDateTime.now() );

        atualizarClienteEnderecoUseCase.execute(clienteEndereco);

        logger.info("Endereço de cliente atualizado com sucesso: {}", clienteEndereco.getId());

        return ResponseEntity.status(200).build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remove um endereço de cliente pelo ID do endereço de cliente")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id)
    {
        logger.info("Iniciando remoção do endereço de cliente com ID: {}", id);

        removerClienteEnderecoUseCase.execute(id);

        logger.info("Endereço de cliente removido com sucesso: {}", id);

        return ResponseEntity.ok().build();
    }

    @GetMapping
    @Operation(summary = "Consulta todos os endereços de clientes")
    public ResponseEntity<List<ClienteEndereco>> findAll(@RequestParam(value = "page", defaultValue = "1") int page, @RequestParam(value = "size", defaultValue = "15") int size)
    {
        logger.info("Iniciando consulta de todos os endereços de clientes. Página: {}, Tamanho: {}", page, size);

        Pageable pageable = PageRequest.of(page - 1, size);
        List<ClienteEndereco> clienteEnderecos = consultarTodosClienteEnderecoUseCase.execute(pageable);

        logger.info("Consulta de todos os endereços de clientes concluída. Total de endereços de clientes encontrados: {}", clienteEnderecos.size());

        return ResponseEntity.ok(clienteEnderecos);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Consulta um endereço de cliente pelo ID do endereço de cliente")
    public ResponseEntity<Optional<ClienteEndereco>> findById(@PathVariable Long id)
    {
        logger.info("Iniciando consulta do clienteEndereco com ID: {}", id);

        Optional<ClienteEndereco> clienteEndereco = consultarUmClienteEnderecoUseCase.execute(id);

        if (clienteEndereco.isPresent()) {
            logger.info("Endereço de cliente encontrado: {}", clienteEndereco.get().getId());
        } else {
            logger.warn("Endereço de cliente com ID {} não encontrado", id);
        }

        return ResponseEntity.ok(clienteEndereco);
    }
}
