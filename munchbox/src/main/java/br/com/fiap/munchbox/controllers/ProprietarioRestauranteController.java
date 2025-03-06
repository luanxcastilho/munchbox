package br.com.fiap.munchbox.controllers;

import br.com.fiap.munchbox.dtos.ProprietarioRestauranteRequestDTO;
import br.com.fiap.munchbox.entities.ProprietarioRestaurante;
import br.com.fiap.munchbox.services.ProprietarioRestauranteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/proprietarios-restaurantes")
@Tag(
        name = "Proprietários de restaurantes",
        description = "Gerenciamento de proprietários de restaurantes"
)
public class ProprietarioRestauranteController {

    private final Logger logger = LoggerFactory.getLogger(ProprietarioRestauranteController.class);

    private final ProprietarioRestauranteService proprietarioRestauranteService;

    public ProprietarioRestauranteController(ProprietarioRestauranteService proprietarioRestauranteService) {
        this.proprietarioRestauranteService = proprietarioRestauranteService;
    }

    @GetMapping
    @Operation(
            summary = "Lista todos os proprietários de restaurantes"
    )
    public ResponseEntity<List<ProprietarioRestaurante>> findAll(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "15") int size
    ) {
        logger.info("GET /v1/proprietarios-restaurantes PAGE: "+page+" SIZE: "+size);
        var proprietarioRestaurante = proprietarioRestauranteService.findAll(page, size);
        return ResponseEntity.ok(proprietarioRestaurante);
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Busca um proprietário de restaurante pelo ID do proprietário de restaurante"
    )
    public ResponseEntity<Optional<ProprietarioRestaurante>> findById(
            @PathVariable("id") Long id
    ){
        logger.info("GET /v1/proprietarios-restaurantes/"+id);
        var proprietarioRestaurante = this.proprietarioRestauranteService.findById(id);
        return ResponseEntity.ok(proprietarioRestaurante);
    }

    @PostMapping
    @Operation(
            summary = "Cadastra um novo proprietário de restaurante"
    )
    public ResponseEntity<ProprietarioRestaurante> create(
            @RequestBody ProprietarioRestauranteRequestDTO proprietarioRestaurante
    ){
        logger.info("POST /v1/proprietarios-restaurantes BODY: "+proprietarioRestaurante);
        this.proprietarioRestauranteService.create(proprietarioRestaurante);
        return ResponseEntity.status(201).build();
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Atualiza um proprietário de restaurante pelo ID do proprietário de restaurante"
    )
    public ResponseEntity<ProprietarioRestaurante> update(
            @PathVariable("id") Long id,
            @RequestBody ProprietarioRestauranteRequestDTO proprietarioRestaurante
    ){
        logger.info("PUT /v1/proprietarios-restaurantes/"+id+" BODY: "+proprietarioRestaurante);
        this.proprietarioRestauranteService.update(proprietarioRestaurante, id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Remove um proprietário de restaurante pelo ID do proprietário de restaurante"
    )
    public ResponseEntity<Void> delete(
            @PathVariable("id") Long id
    ){
        logger.info("DELETE /v1/proprietarios-restaurantes/"+id);
        this.proprietarioRestauranteService.delete(id);
        return ResponseEntity.ok().build();
    }

}
