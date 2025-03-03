package br.com.fiap.munchbox.controllers;

import br.com.fiap.munchbox.dtos.RestauranteProdutoRequestDTO;
import br.com.fiap.munchbox.entities.RestauranteProduto;
import br.com.fiap.munchbox.services.RestauranteProdutoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/restaurantes-produtos")
@Tag(
        name = "Produtos de restaurantes",
        description = "Gerenciamento de produtos de restaurantes"
)
public class RestauranteProdutoController
{
    private final Logger logger = LoggerFactory.getLogger(RestauranteProdutoController.class);
    private final RestauranteProdutoService restauranteProdutoService;

    public RestauranteProdutoController(final RestauranteProdutoService restauranteProdutoService)
    {
        this.restauranteProdutoService = restauranteProdutoService;
    }

    @GetMapping
    @Operation(summary = "Lista todos os produtos de restaurantes")
    public ResponseEntity<List<RestauranteProduto>> findAll(@RequestParam(value = "page", defaultValue = "1") int page, @RequestParam(value = "size", defaultValue = "15") int size)
    {
        logger.info("GET /v1/restaurantes-produtos PAGE: " + page + " SIZE: " + size);
        var restauranteProduto = restauranteProdutoService.findAll(page, size);
        return ResponseEntity.ok(restauranteProduto);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca um produto pelo ID do produto")
    public ResponseEntity<Optional<RestauranteProduto>> findById(@PathVariable("id") Long id)
    {
        logger.info("GET /v1/restaurantes-produtos/" + id);
        var restauranteProduto = this.restauranteProdutoService.findById(id);
        return ResponseEntity.ok(restauranteProduto);
    }

    @PostMapping
    @Operation(summary = "Cadastra um novo produto")
    public ResponseEntity<RestauranteProduto> create(@RequestBody RestauranteProdutoRequestDTO restauranteProduto)
    {
        logger.info("POST /v1/restaurantes-produtos BODY: " + restauranteProduto);
        this.restauranteProdutoService.create(restauranteProduto);
        return ResponseEntity.status(201).build();
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza um produto pelo ID do produto")
    public ResponseEntity<RestauranteProduto> update(@PathVariable("id") Long id, @RequestBody RestauranteProdutoRequestDTO restauranteProduto)
    {
        logger.info("PUT /v1/restaurantes-produtos/" + id + " BODY: " + restauranteProduto);
        this.restauranteProdutoService.update(restauranteProduto, id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remove um produto pelo ID do produto")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id)
    {
        logger.info("DELETE /v1/restaurantes-produtos/" + id);
        this.restauranteProdutoService.delete(id);
        return ResponseEntity.ok().build();
    }
}
