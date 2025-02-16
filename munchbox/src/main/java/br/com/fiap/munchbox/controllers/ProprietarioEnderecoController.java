package br.com.fiap.munchbox.controllers;

import br.com.fiap.munchbox.dtos.proprietarioendereco.ProprietarioEnderecoRequestDTO;
import br.com.fiap.munchbox.entities.ProprietarioEndereco;
import br.com.fiap.munchbox.services.ProprietarioEnderecoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/proprietarios-enderecos")
@Tag(
        name = "Endereços de proprietários",
        description = "Gerenciamento de endereços de proprietários"
)
public class ProprietarioEnderecoController {

    private final Logger logger = LoggerFactory.getLogger(ProprietarioEnderecoController.class);
    
    private final ProprietarioEnderecoService proprietarioEnderecoService;
    
    public ProprietarioEnderecoController(final ProprietarioEnderecoService proprietarioEnderecoService) {
        this.proprietarioEnderecoService = proprietarioEnderecoService;
    }

    @GetMapping
    @Operation(
            summary = "Lista todos os endereços de proprietários"
    )
    public ResponseEntity<List<ProprietarioEndereco>> findAll(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "15") int size
    ) {
        logger.info("GET /v1/proprietarios-enderecos PAGE: "+page+" SIZE: "+size);
        var proprietarioEndereco = proprietarioEnderecoService.findAll(page, size);
        return ResponseEntity.ok(proprietarioEndereco);
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Busca um endereço de proprietário pelo ID do endereço"
    )
    public ResponseEntity<Optional<ProprietarioEndereco>> findById(
            @PathVariable("id") Long id
    ){
        logger.info("GET /v1/proprietarios-enderecos/"+id);
        var proprietarioEndereco = this.proprietarioEnderecoService.findById(id);
        return ResponseEntity.ok(proprietarioEndereco);
    }

    @PostMapping
    @Operation(
            summary = "Cadastra um nov endereço de proprietário"
    )
    public ResponseEntity<ProprietarioEndereco> create(
            @RequestBody ProprietarioEnderecoRequestDTO proprietarioEndereco
    ){
        logger.info("POST /v1/proprietarios-enderecos BODY: "+proprietarioEndereco);
        this.proprietarioEnderecoService.create(proprietarioEndereco);
        return ResponseEntity.status(201).build();
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Atualiza um endereço de proprietário pelo ID do endereço"
    )
    public ResponseEntity<ProprietarioEndereco> update(
            @PathVariable("id") Long id,
            @RequestBody ProprietarioEnderecoRequestDTO proprietarioEndereco
    ){
        logger.info("PUT /v1/proprietarios-enderecos/"+id+" BODY: "+proprietarioEndereco);
        this.proprietarioEnderecoService.update(proprietarioEndereco, id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Remove um endereço de proprietário pelo ID do endereço"
    )
    public ResponseEntity<Void> delete(
            @PathVariable("id") Long id
    ){
        logger.info("DELETE /v1/proprietarios-enderecos/"+id);
        this.proprietarioEnderecoService.delete(id);
        return ResponseEntity.ok().build();
    }
}
