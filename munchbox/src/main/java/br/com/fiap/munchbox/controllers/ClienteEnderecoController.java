package br.com.fiap.munchbox.controllers;

import br.com.fiap.munchbox.dtos.clienteendereco.ClienteEnderecoRequestDTO;
import br.com.fiap.munchbox.entities.ClienteEndereco;
import br.com.fiap.munchbox.services.ClienteEnderecoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/cliente-enderecos")
@Tag(
        name = "Endereços de clientes",
        description = "Gerenciamento de endereços de clientes"
)
public class ClienteEnderecoController {

    private final Logger logger = LoggerFactory.getLogger(ClienteEnderecoController.class);
    
    private final ClienteEnderecoService clienteEnderecoService;
    
    public ClienteEnderecoController(final ClienteEnderecoService clienteEnderecoService) {
        this.clienteEnderecoService = clienteEnderecoService;
    }

    @GetMapping
    @Operation(
            summary = "Lista todos os endereços de clientes"
    )
    public ResponseEntity<List<ClienteEndereco>> findAll(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "15") int size
    ) {
        logger.info("GET /v1/cliente-enderecos PAGE: "+page+" SIZE: "+size);
        var clienteEndereco = clienteEnderecoService.findAll(page, size);
        return ResponseEntity.ok(clienteEndereco);
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Busca um endereço de cliente pelo ID do endereço"
    )
    public ResponseEntity<Optional<ClienteEndereco>> findById(
            @PathVariable("id") Long id
    ){
        logger.info("GET /v1/cliente-enderecos/"+id);
        var clienteEndereco = this.clienteEnderecoService.findById(id);
        return ResponseEntity.ok(clienteEndereco);
    }

    @PostMapping
    @Operation(
            summary = "Cadastra um novo endereço de cliente"
    )
    public ResponseEntity<ClienteEndereco> create(
            @RequestBody ClienteEnderecoRequestDTO clienteEndereco
    ){
        logger.info("POST /v1/cliente-enderecos BODY: "+clienteEndereco);
        this.clienteEnderecoService.create(clienteEndereco);
        return ResponseEntity.status(201).build();
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Atualiza um endereço de cliente pelo ID do endereço"
    )
    public ResponseEntity<ClienteEndereco> update(
            @PathVariable("id") Long id,
            @RequestBody ClienteEnderecoRequestDTO clienteEndereco
    ){
        logger.info("PUT /v1/cliente-enderecos/"+id+" BODY: "+clienteEndereco);
        this.clienteEnderecoService.update(clienteEndereco, id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Remove um endereço de cliente pelo ID do endereço"
    )
    public ResponseEntity<Void> delete(
            @PathVariable("id") Long id
    ){
        logger.info("DELETE /v1/cliente-enderecos/"+id);
        this.clienteEnderecoService.delete(id);
        return ResponseEntity.ok().build();
    }
}
