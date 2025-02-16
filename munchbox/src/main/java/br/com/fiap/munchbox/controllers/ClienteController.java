package br.com.fiap.munchbox.controllers;

import br.com.fiap.munchbox.dtos.cliente.ClienteRequestDTO;
import br.com.fiap.munchbox.entities.Cliente;
import br.com.fiap.munchbox.services.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/clientes")
@Tag(
        name = "Clientes",
        description = "Gerenciamento de clientes"
)
public class ClienteController {

    private final Logger logger = LoggerFactory.getLogger(ClienteController.class);

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    @Operation(
            summary = "Lista todos os clientes"
    )
    public ResponseEntity<List<Cliente>> findAll(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "15") int size
    ) {
        logger.info("GET /v1/clientes PAGE: "+page+" SIZE: "+size);
        var cliente = clienteService.findAll(page, size);
        return ResponseEntity.ok(cliente);
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Busca um cliente pelo ID do cliente"
    )
    public ResponseEntity<Optional<Cliente>> findById(
            @PathVariable("id") Long id
    ){
        logger.info("GET /v1/clientes/"+id);
        var cliente = this.clienteService.findById(id);
        return ResponseEntity.ok(cliente);
    }

    @PostMapping
    @Operation(
            summary = "Cadastra um novo cliente"
    )
    public ResponseEntity<Cliente> create(
            @Valid @RequestBody ClienteRequestDTO clienteRequestDTO
    ){
        logger.info("POST /v1/clientes BODY: "+clienteRequestDTO);
        this.clienteService.create(clienteRequestDTO);
        return ResponseEntity.status(201).build();
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Atualiza um cliente pelo ID do cliente"
    )
    public ResponseEntity<Cliente> update(
            @PathVariable("id") Long id,
            @Valid @RequestBody ClienteRequestDTO clienteRequestDTO
    ){
        logger.info("PUT /v1/clientes/"+id+" BODY: "+clienteRequestDTO);
        this.clienteService.update(clienteRequestDTO, id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Remove um cliente pelo ID do cliente"
    )
    public ResponseEntity<Void> delete(
            @PathVariable("id") Long id
    ){
        logger.info("DELETE /v1/clientes/"+id);
        this.clienteService.delete(id);
        return ResponseEntity.ok().build();
    }
    
}
