package br.com.fiap.munchbox.interfaces.controller;

import br.com.fiap.munchbox.interfaces.dto.RestauranteTipoCozinhaRequestDTO;
import br.com.fiap.munchbox.usecase.restaurantetipocozinha.*;
import br.com.fiap.munchbox.usecase.usuario.ConsultarUmUsuarioUseCase;
import br.com.fiap.munchbox.domain.core.RestauranteTipoCozinha;
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
@RequestMapping("/v1/restaurantes-tipos-cozinhas")
@Tag(name = "Tipos de cozinhas de restaurantes", description = "Gerenciamento de tipos de cozinhas de restaurantes")
public class RestauranteTipoCozinhaController
{
    private final Logger logger = LoggerFactory.getLogger(RestauranteTipoCozinhaController.class);

    private final CadastrarRestauranteTipoCozinhaUseCase cadastrarRestauranteTipoCozinhaUseCase;
    private final AtualizarRestauranteTipoCozinhaUseCase atualizarRestauranteTipoCozinhaUseCase;
    private final RemoverRestauranteTipoCozinhaUseCase removerRestauranteTipoCozinhaUseCase;
    private final ConsultarTodosRestauranteTipoCozinhaUseCase consultarTodosRestauranteTipoCozinhaUseCase;
    private final ConsultarUmRestauranteTipoCozinhaUseCase consultarUmRestauranteTipoCozinhaUseCase;

    private final ConsultarUmUsuarioUseCase consultarUmUsuarioUseCase;

    public RestauranteTipoCozinhaController(
            CadastrarRestauranteTipoCozinhaUseCase cadastrarRestauranteTipoCozinhaUseCase,
            AtualizarRestauranteTipoCozinhaUseCase atualizarRestauranteTipoCozinhaUseCase,
            RemoverRestauranteTipoCozinhaUseCase removerRestauranteTipoCozinhaUseCase,
            ConsultarTodosRestauranteTipoCozinhaUseCase consultarTodosRestauranteTipoCozinhaUseCase,
            ConsultarUmRestauranteTipoCozinhaUseCase consultarUmRestauranteTipoCozinhaUseCase,
            ConsultarUmUsuarioUseCase consultarUmUsuarioUseCase)
    {
        this.cadastrarRestauranteTipoCozinhaUseCase = cadastrarRestauranteTipoCozinhaUseCase;
        this.atualizarRestauranteTipoCozinhaUseCase = atualizarRestauranteTipoCozinhaUseCase;
        this.removerRestauranteTipoCozinhaUseCase = removerRestauranteTipoCozinhaUseCase;
        this.consultarTodosRestauranteTipoCozinhaUseCase = consultarTodosRestauranteTipoCozinhaUseCase;
        this.consultarUmRestauranteTipoCozinhaUseCase = consultarUmRestauranteTipoCozinhaUseCase;
        this.consultarUmUsuarioUseCase = consultarUmUsuarioUseCase;
    }


    @PostMapping
    @Operation(summary = "Cadastra um novo tipo de cozinha de restaurante")
    public ResponseEntity<RestauranteTipoCozinha> create(@RequestBody RestauranteTipoCozinhaRequestDTO restauranteTipoCozinhaRequestDTO)
    {
        logger.info("Iniciando cadastro de tipo de cozinha de restaurante: {}",
                    restauranteTipoCozinhaRequestDTO.getNome());

        RestauranteTipoCozinha restauranteTipoCozinha = new RestauranteTipoCozinha();

        restauranteTipoCozinha.setNome(restauranteTipoCozinhaRequestDTO.getNome());
        restauranteTipoCozinha.setDataAtualizacao(LocalDateTime.now());
        restauranteTipoCozinha.setDataInclusao(LocalDateTime.now());

        cadastrarRestauranteTipoCozinhaUseCase.execute(restauranteTipoCozinha);

        logger.info("Tipo de cozinha de restaurante cadastrado com sucesso: {}",
                    restauranteTipoCozinha.getNome());

        return ResponseEntity.status(201)
                .build();
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza um tipo de cozinha de restaurante pelo ID do tipo de cozinha de restaurante")
    public ResponseEntity<RestauranteTipoCozinha> create(
            @RequestBody RestauranteTipoCozinhaRequestDTO restauranteTipoCozinhaRequestDTO,
            @PathVariable Long id)
    {
        logger.info("Iniciando atualização do tipo de cozinha de restaurante com ID: {}",
                    id);

        RestauranteTipoCozinha restauranteTipoCozinha = consultarUmRestauranteTipoCozinhaUseCase.execute(id)
                .orElseThrow(() -> new RuntimeException("tipo de cozinha de restaurante não encontrado"));

        restauranteTipoCozinha.setNome(restauranteTipoCozinhaRequestDTO.getNome());
        restauranteTipoCozinha.setDataAtualizacao(LocalDateTime.now());

        atualizarRestauranteTipoCozinhaUseCase.execute(restauranteTipoCozinha);

        logger.info("Tipo de cozinha de restaurante atualizado com sucesso: {}",
                    restauranteTipoCozinha.getNome());

        return ResponseEntity.status(200)
                .build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remove um tipo de cozinha de restaurante pelo ID do tipo de cozinha de restaurante")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id)
    {
        logger.info("Iniciando remoção do tipo de cozinha de restaurante com ID: {}",
                    id);

        removerRestauranteTipoCozinhaUseCase.execute(id);

        logger.info("Tipo de cozinha de restaurante removido com sucesso: {}",
                    id);

        return ResponseEntity.ok()
                .build();
    }

    @GetMapping
    @Operation(summary = "Consulta todos os tipos de cozinha de restaurante")
    public ResponseEntity<List<RestauranteTipoCozinha>> findAll(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "15") int size)
    {
        logger.info("Iniciando consulta de todos os tipos de cozinha de restaurante. Página: {}, Tamanho: {}",
                    page,
                    size);

        Pageable pageable = PageRequest.of(page - 1,
                                           size);
        List<RestauranteTipoCozinha> restauranteTipoCozinhas = consultarTodosRestauranteTipoCozinhaUseCase.execute(pageable);

        logger.info("Consulta de todos os tipos de cozinha de restaurante concluída. Total de tipos de cozinha de restaurante encontrados: {}",
                    restauranteTipoCozinhas.size());

        return ResponseEntity.ok(restauranteTipoCozinhas);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Consulta um tipo de cozinha de restaurante pelo ID do tipo de cozinha de restaurante")
    public ResponseEntity<Optional<RestauranteTipoCozinha>> findById(@PathVariable Long id)
    {
        logger.info("Iniciando consulta do tipo de cozinha de restaurante com ID: {}",
                    id);

        Optional<RestauranteTipoCozinha> restauranteTipoCozinha = consultarUmRestauranteTipoCozinhaUseCase.execute(id);

        if (restauranteTipoCozinha.isPresent()) {
            logger.info("Tipo de cozinha de restaurante encontrado: {}",
                        restauranteTipoCozinha.get()
                                .getNome());
        }
        else {
            logger.warn("tipo de cozinha de restaurante com ID {} não encontrado",
                        id);
        }

        return ResponseEntity.ok(restauranteTipoCozinha);
    }
}
