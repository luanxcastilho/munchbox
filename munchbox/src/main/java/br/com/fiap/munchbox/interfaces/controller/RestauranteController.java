package br.com.fiap.munchbox.interfaces.controller;

import br.com.fiap.munchbox.domain.core.Proprietario;
import br.com.fiap.munchbox.interfaces.dto.RestauranteRequestDTO;
import br.com.fiap.munchbox.usecase.proprietario.ConsultarUmProprietarioUseCase;
import br.com.fiap.munchbox.usecase.restaurante.*;
import br.com.fiap.munchbox.usecase.restaurantetipocozinha.ConsultarUmRestauranteTipoCozinhaUseCase;
import br.com.fiap.munchbox.domain.core.Restaurante;
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
@RequestMapping("/v1/restaurantes")
@Tag(name = "Restaurantes", description = "Gerenciamento de restaurantes")
public class RestauranteController
{
    private final Logger logger = LoggerFactory.getLogger(RestauranteController.class);

    private final CadastrarRestauranteUseCase cadastrarRestauranteUseCase;
    private final AtualizarRestauranteUseCase atualizarRestauranteUseCase;
    private final RemoverRestauranteUseCase removerRestauranteUseCase;
    private final ConsultarTodosRestauranteUseCase consultarTodosRestauranteUseCase;
    private final ConsultarUmRestauranteUseCase consultarUmRestauranteUseCase;

    private final ConsultarUmRestauranteTipoCozinhaUseCase consultarUmRestauranteTipoCozinhaUseCase;
    private final ConsultarUmProprietarioUseCase consultarUmProprietarioUseCase;

    public RestauranteController(
            CadastrarRestauranteUseCase cadastrarRestauranteUseCase,
            AtualizarRestauranteUseCase atualizarRestauranteUseCase,
            RemoverRestauranteUseCase removerRestauranteUseCase,
            ConsultarTodosRestauranteUseCase consultarTodosRestauranteUseCase,
            ConsultarUmRestauranteUseCase consultarUmRestauranteUseCase,
            ConsultarUmRestauranteTipoCozinhaUseCase consultarUmRestauranteTipoCozinhaUseCase,
            ConsultarUmProprietarioUseCase consultarUmProprietarioUseCase)
    {
        this.cadastrarRestauranteUseCase = cadastrarRestauranteUseCase;
        this.atualizarRestauranteUseCase = atualizarRestauranteUseCase;
        this.removerRestauranteUseCase = removerRestauranteUseCase;
        this.consultarTodosRestauranteUseCase = consultarTodosRestauranteUseCase;
        this.consultarUmRestauranteUseCase = consultarUmRestauranteUseCase;
        this.consultarUmRestauranteTipoCozinhaUseCase = consultarUmRestauranteTipoCozinhaUseCase;
        this.consultarUmProprietarioUseCase = consultarUmProprietarioUseCase;
    }


    @PostMapping
    @Operation(summary = "Cadastra um novo restaurante")
    public ResponseEntity<Restaurante> create(@RequestBody RestauranteRequestDTO restauranteRequestDTO)
    {
        logger.info("Iniciando cadastro de restaurante: {}",
                    restauranteRequestDTO.getNome());

        RestauranteTipoCozinha restauranteTipoCozinha = consultarUmRestauranteTipoCozinhaUseCase.execute(restauranteRequestDTO.getIdRestauranteTipoCozinha())
                .orElseThrow(() -> new RuntimeException("Tipo de cozinha não encontrado"));

        Proprietario proprietario = consultarUmProprietarioUseCase.execute(restauranteRequestDTO.getIdProprietario())
                .orElseThrow(() -> new RuntimeException("Proprietário não encontrado"));

        Restaurante restaurante = new Restaurante();

        restaurante.setNome(restauranteRequestDTO.getNome());
        restaurante.setRestauranteTipoCozinha(restauranteTipoCozinha);
        restaurante.setProprietario(proprietario);
        restaurante.setRua(restauranteRequestDTO.getRua());
        restaurante.setNumero(restauranteRequestDTO.getNumero());
        restaurante.setComplemento(restauranteRequestDTO.getComplemento());
        restaurante.setBairro(restauranteRequestDTO.getBairro());
        restaurante.setCidade(restauranteRequestDTO.getCidade());
        restaurante.setEstado(restauranteRequestDTO.getEstado());
        restaurante.setCep(restauranteRequestDTO.getCep());
        restaurante.setDataAtualizacao(LocalDateTime.now());
        restaurante.setDataInclusao(LocalDateTime.now());

        cadastrarRestauranteUseCase.execute(restaurante);

        logger.info("Restaurante cadastrado com sucesso: {}",
                    restaurante.getNome());

        return ResponseEntity.status(201)
                .build();
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza um restaurante pelo ID do restaurante")
    public ResponseEntity<Restaurante> create(
            @RequestBody RestauranteRequestDTO restauranteRequestDTO,
            @PathVariable Long id)
    {
        logger.info("Iniciando atualização do restaurante com ID: {}",
                    id);

        Restaurante restaurante = consultarUmRestauranteUseCase.execute(id)
                .orElseThrow(() -> new RuntimeException("Restaurante não encontrado"));

        RestauranteTipoCozinha restauranteTipoCozinha = consultarUmRestauranteTipoCozinhaUseCase.execute(restauranteRequestDTO.getIdRestauranteTipoCozinha())
                .orElseThrow(() -> new RuntimeException("Tipo de cozinha não encontrado"));

        Proprietario proprietario = consultarUmProprietarioUseCase.execute(restauranteRequestDTO.getIdProprietario())
                .orElseThrow(() -> new RuntimeException("Proprietário não encontrado"));

        restaurante.setNome(restauranteRequestDTO.getNome());
        restaurante.setRestauranteTipoCozinha(restauranteTipoCozinha);
        restaurante.setProprietario(proprietario);
        restaurante.setRua(restauranteRequestDTO.getRua());
        restaurante.setNumero(restauranteRequestDTO.getNumero());
        restaurante.setComplemento(restauranteRequestDTO.getComplemento());
        restaurante.setBairro(restauranteRequestDTO.getBairro());
        restaurante.setCidade(restauranteRequestDTO.getCidade());
        restaurante.setEstado(restauranteRequestDTO.getEstado());
        restaurante.setCep(restauranteRequestDTO.getCep());
        restaurante.setDataAtualizacao(LocalDateTime.now());

        atualizarRestauranteUseCase.execute(restaurante);

        logger.info("Restaurante atualizado com sucesso: {}",
                    restaurante.getId());

        return ResponseEntity.status(200)
                .build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remove um restaurante pelo ID do restaurante")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id)
    {
        logger.info("Iniciando remoção do restaurante com ID: {}",
                    id);

        removerRestauranteUseCase.execute(id);

        logger.info("Restaurante removido com sucesso: {}",
                    id);

        return ResponseEntity.ok()
                .build();
    }

    @GetMapping
    @Operation(summary = "Consulta todos os restaurantes")
    public ResponseEntity<List<Restaurante>> findAll(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "15") int size)
    {
        logger.info("Iniciando consulta de todos os restaurantes. Página: {}, Tamanho: {}",
                    page,
                    size);

        Pageable pageable = PageRequest.of(page - 1,
                                           size);
        List<Restaurante> restaurantes = consultarTodosRestauranteUseCase.execute(pageable);

        logger.info("Consulta de todos os restaurantes concluída. Total de restaurantes encontrados: {}",
                    restaurantes.size());

        return ResponseEntity.ok(restaurantes);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Consulta um restaurante pelo ID do restaurante")
    public ResponseEntity<Optional<Restaurante>> findById(@PathVariable Long id)
    {
        logger.info("Iniciando consulta do restaurante com ID: {}",
                    id);

        Optional<Restaurante> restaurante = consultarUmRestauranteUseCase.execute(id);

        if (restaurante.isPresent()) {
            logger.info("Restaurante encontrado: {}",
                        restaurante.get()
                                .getNome());
        }
        else {
            logger.warn("Restaurante com ID {} não encontrado",
                        id);
        }

        return ResponseEntity.ok(restaurante);
    }
}
