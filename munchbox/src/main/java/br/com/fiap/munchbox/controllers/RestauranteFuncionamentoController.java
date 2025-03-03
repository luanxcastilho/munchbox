package br.com.fiap.munchbox.controllers;

import br.com.fiap.munchbox.dtos.RestauranteFuncionamentoRequestDTO;
import br.com.fiap.munchbox.entities.RestauranteFuncionamento;
import br.com.fiap.munchbox.services.RestauranteFuncionamentoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/restaurantes-funcionamentos")
@Tag(name = "Horários de funcionamento de restaurantes", description = "Gerenciamento de horários de funcionamento dos restaurantes")
public class RestauranteFuncionamentoController
{
    private final Logger logger = LoggerFactory.getLogger(br.com.fiap.munchbox.controllers.RestauranteFuncionamentoController.class);
    private final RestauranteFuncionamentoService restauranteFuncionamentoService;

    public RestauranteFuncionamentoController(final RestauranteFuncionamentoService restauranteFuncionamentoService)
    {
        this.restauranteFuncionamentoService = restauranteFuncionamentoService;
    }

    @GetMapping
    @Operation(summary = "Lista todos os horários de funcionamento dos restaurantes")
    public ResponseEntity<List<RestauranteFuncionamento>> findAll(@RequestParam(value = "page", defaultValue = "1") int page, @RequestParam(value = "size", defaultValue = "15") int size)
    {
        logger.info("GET /v1/restaurantes-funcionamentos PAGE: " + page + " SIZE: " + size);
        var restauranteFuncionamento = restauranteFuncionamentoService.findAll(page, size);
        return ResponseEntity.ok(restauranteFuncionamento);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca um horário de funcionamento pelo ID do horário de funcionamento")
    public ResponseEntity<Optional<RestauranteFuncionamento>> findById(@PathVariable("id") Long id)
    {
        logger.info("GET /v1/restaurantes-funcionamentos/" + id);
        var restauranteFuncionamento = this.restauranteFuncionamentoService.findById(id);
        return ResponseEntity.ok(restauranteFuncionamento);
    }

    @PostMapping
    @Operation(summary = "Cadastra um novo horário de funcionamento")
    public ResponseEntity<RestauranteFuncionamento> create(@RequestBody RestauranteFuncionamentoRequestDTO restauranteFuncionamento)
    {
        logger.info("POST /v1/restaurantes-funcionamentos BODY: " + restauranteFuncionamento);
        this.restauranteFuncionamentoService.create(restauranteFuncionamento);
        return ResponseEntity.status(201).build();
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza um horário de funcionamento pelo ID do horário de funcionamento")
    public ResponseEntity<RestauranteFuncionamento> update(@PathVariable("id") Long id, @RequestBody RestauranteFuncionamentoRequestDTO restauranteFuncionamento)
    {
        logger.info("PUT /v1/restaurantes-funcionamentos/" + id + " BODY: " + restauranteFuncionamento);
        this.restauranteFuncionamentoService.update(restauranteFuncionamento, id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remove um horário de funcionamento pelo ID do horário de funcionamento")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id)
    {
        logger.info("DELETE /v1/restaurantes-funcionamentos/" + id);
        this.restauranteFuncionamentoService.delete(id);
        return ResponseEntity.ok().build();
    }
}
