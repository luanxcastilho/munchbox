package br.com.fiap.munchbox.controllers;

import br.com.fiap.munchbox.dtos.ProprietarioRequestDTO;
import br.com.fiap.munchbox.entities.Proprietario;
import br.com.fiap.munchbox.services.ProprietarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/proprietarios")
@Tag(
        name = "Proprietários",
        description = "Gerenciamento de proprietários"
)
public class ProprietarioController {

    private final Logger logger = LoggerFactory.getLogger(ProprietarioController.class);

    private final ProprietarioService proprietarioService;

    public ProprietarioController(ProprietarioService proprietarioService) {
        this.proprietarioService = proprietarioService;
    }

    @GetMapping
    @Operation(
            summary = "Lista todos os proprietários"
    )
    public ResponseEntity<List<Proprietario>> findAll(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "15") int size
    ) {
        logger.info("GET /v1/proprietarios PAGE: "+page+" SIZE: "+size);
        var proprietario = proprietarioService.findAll(page, size);
        return ResponseEntity.ok(proprietario);
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Busca um proprietário pelo ID do proprietário"
    )
    public ResponseEntity<Optional<Proprietario>> findById(
            @PathVariable("id") Long id
    ){
        logger.info("GET /v1/proprietarios/"+id);
        var proprietario = this.proprietarioService.findById(id);
        return ResponseEntity.ok(proprietario);
    }

    @PostMapping
    @Operation(
            summary = "Cadastra um novo proprietário"
    )
    public ResponseEntity<Proprietario> create(
            @RequestBody ProprietarioRequestDTO proprietario
    ){
        logger.info("POST /v1/proprietarios BODY: "+proprietario);
        this.proprietarioService.create(proprietario);
        return ResponseEntity.status(201).build();
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Atualiza um proprietário pelo ID do proprietário"
    )
    public ResponseEntity<Proprietario> update(
            @PathVariable("id") Long id,
            @RequestBody ProprietarioRequestDTO proprietario
    ){
        logger.info("PUT /v1/proprietarios/"+id+" BODY: "+proprietario);
        this.proprietarioService.update(proprietario, id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Remove um proprietário pelo ID do proprietário"
    )
    public ResponseEntity<Void> delete(
            @PathVariable("id") Long id
    ){
        logger.info("DELETE /v1/proprietarios/"+id);
        this.proprietarioService.delete(id);
        return ResponseEntity.ok().build();
    }
    
}
