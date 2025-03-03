package br.com.fiap.munchbox.controllers;

import br.com.fiap.munchbox.dtos.RestauranteRequestDTO;
import br.com.fiap.munchbox.entities.Restaurante;
import br.com.fiap.munchbox.services.RestauranteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/restaurantes")
@Tag(
        name = "Restaurantes",
        description = "Gerenciamento de restaurantes"
)
public class RestauranteController {

    private final Logger logger = LoggerFactory.getLogger(RestauranteController.class);
    private final RestauranteService restauranteService;

    public RestauranteController(final RestauranteService restauranteService) {
        this.restauranteService = restauranteService;
    }

    @GetMapping
    @Operation(
            summary = "Lista todos os restaurantes"
    )
    public ResponseEntity<List<Restaurante>> findAll(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "15") int size
    ) {
        logger.info("GET /v1/proprietarios-enderecos PAGE: "+page+" SIZE: "+size);
        var restaurante = restauranteService.findAll(page, size);
        return ResponseEntity.ok(restaurante);
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Busca um restaurante pelo ID do restaurante"
    )
    public ResponseEntity<Optional<Restaurante>> findById(
            @PathVariable("id") Long id
    ){
        logger.info("GET /v1/restaurantes/"+id);
        var restaurante = this.restauranteService.findById(id);
        return ResponseEntity.ok(restaurante);
    }

    @PostMapping
    @Operation(
            summary = "Cadastra um novo restaurante"
    )
    public ResponseEntity<Restaurante> create(
            @RequestBody RestauranteRequestDTO restaurante
    ){
        logger.info("POST /v1/restaurantes BODY: "+restaurante);
        this.restauranteService.create(restaurante);
        return ResponseEntity.status(201).build();
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Atualiza um restaurante pelo ID do restaurante"
    )
    public ResponseEntity<Restaurante> update(
            @PathVariable("id") Long id,
            @RequestBody RestauranteRequestDTO restaurante
    ){
        logger.info("PUT /v1/restaurantes/"+id+" BODY: "+restaurante);
        this.restauranteService.update(restaurante, id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Remove um restaurante pelo ID do restaurante"
    )
    public ResponseEntity<Void> delete(
            @PathVariable("id") Long id
    ){
        logger.info("DELETE /v1/restaurantes/"+id);
        this.restauranteService.delete(id);
        return ResponseEntity.ok().build();
    }
}

