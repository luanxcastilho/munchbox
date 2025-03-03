package br.com.fiap.munchbox.controllers;

import br.com.fiap.munchbox.dtos.RestauranteTipoCozinhaRequestDTO;
import br.com.fiap.munchbox.entities.RestauranteTipoCozinha;
import br.com.fiap.munchbox.services.RestauranteTipoCozinhaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/restaurantes-tipos-cozinhas")
@Tag(
        name = "Tipos de cozinhas de restaurantes",
        description = "Gerenciamento de tipos de cozinhas de restaurantes"
)
public class RestauranteTipoCozinhaController
{

    private final Logger logger = LoggerFactory.getLogger(RestauranteTipoCozinhaController.class);

    private final RestauranteTipoCozinhaService restauranteTipoCozinhaService;

    public RestauranteTipoCozinhaController(RestauranteTipoCozinhaService restauranteTipoCozinhaService)
    {
        this.restauranteTipoCozinhaService = restauranteTipoCozinhaService;
    }

    @GetMapping
    @Operation(
            summary = "Lista todos os tipos de cozinha de restaurantes"
    )
    public ResponseEntity<List<RestauranteTipoCozinha>> findAll(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "15") int size
    )
    {
        logger.info("GET /v1/restaurantes-tipos-cozinhas PAGE: " + page + " SIZE: " + size);
        var usuario = restauranteTipoCozinhaService.findAll(
                page,
                size
        );
        return ResponseEntity.ok(usuario);
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Busca um tipo de cozinha pelo ID do tipo de cozinha"
    )
    public ResponseEntity<Optional<RestauranteTipoCozinha>> findById(
            @PathVariable("id") Long id
    )
    {
        logger.info("GET /v1/restaurantes-tipos-cozinhas/" + id);
        var usuario = this.restauranteTipoCozinhaService.findById(id);
        logger.info("    " + usuario.toString());
        return ResponseEntity.ok(usuario);
    }

    @PostMapping
    @Operation(
            summary = "Cadastra um novo tipo de cozinha"
    )
    public ResponseEntity<RestauranteTipoCozinha> create(
            @RequestBody RestauranteTipoCozinhaRequestDTO restauranteTipoCozinhaRequestDTO
    )
    {
        logger.info("POST /v1/restaurantes-tipos-cozinhas BODY: " + restauranteTipoCozinhaRequestDTO);
        this.restauranteTipoCozinhaService.create(restauranteTipoCozinhaRequestDTO);
        return ResponseEntity.status(201).build();
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Atualiza um tipo de cozinha pelo ID do tipo de cozinha"
    )
    public ResponseEntity<RestauranteTipoCozinha> update(
            @PathVariable("id") Long id,
            @RequestBody RestauranteTipoCozinhaRequestDTO restauranteTipoCozinhaRequestDTO
    )
    {
        logger.info("PUT /v1/restaurantes-tipos-cozinhas/" + id + " BODY: " + restauranteTipoCozinhaRequestDTO);
        this.restauranteTipoCozinhaService.update(
                restauranteTipoCozinhaRequestDTO,
                id
        );
        return ResponseEntity.ok().build();
    }


    @DeleteMapping("/{id}")
    @Operation(
            summary = "Remove um tipo de cozinha pelo ID do tipo de cozinha"
    )
    public ResponseEntity<Void> delete(
            @PathVariable("id") Long id
    )
    {
        logger.info("DELETE /v1/restaurantes-tipos-cozinhas/" + id);
        this.restauranteTipoCozinhaService.delete(id);
        return ResponseEntity.ok().build();
    }
}
