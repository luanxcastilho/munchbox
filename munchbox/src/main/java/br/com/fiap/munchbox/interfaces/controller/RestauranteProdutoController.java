package br.com.fiap.munchbox.interfaces.controller;

import br.com.fiap.munchbox.domain.core.Restaurante;
import br.com.fiap.munchbox.domain.core.RestauranteProduto;
import br.com.fiap.munchbox.interfaces.dto.RestauranteProdutoRequestDTO;
import br.com.fiap.munchbox.usecase.restaurante.ConsultarUmRestauranteUseCase;
import br.com.fiap.munchbox.usecase.restaurantefuncionamento.*;
import br.com.fiap.munchbox.usecase.restauranteproduto.*;
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
@RequestMapping("/v1/restaurantes-produtos")
@Tag(name = "Produtos de restaurantes", description = "Gerenciamento de produtos de restaurantes")
public class RestauranteProdutoController {

    private final Logger logger = LoggerFactory.getLogger(RestauranteProdutoController.class);

    private final CadastrarRestauranteProdutoUseCase cadastrarRestauranteProdutoUseCase;
    private final AtualizarRestauranteProdutoUseCase atualizarRestauranteProdutoUseCase;
    private final RemoverRestauranteProdutoUseCase removerRestauranteProdutoUseCase;
    private final ConsultarTodosRestauranteProdutoUseCase consultarTodosRestauranteProdutoUseCase;
    private final ConsultarUmRestauranteProdutoUseCase consultarUmRestauranteProdutoUseCase;
    private final ConsultarUmRestauranteUseCase consultarUmRestauranteUseCase;

    public RestauranteProdutoController(CadastrarRestauranteProdutoUseCase cadastrarRestauranteProdutoUseCase,
                                        AtualizarRestauranteProdutoUseCase atualizarRestauranteProdutoUseCase,
                                        RemoverRestauranteProdutoUseCase removerRestauranteProdutoUseCase,
                                        ConsultarTodosRestauranteProdutoUseCase consultarTodosRestauranteProdutoUseCase,
                                        ConsultarUmRestauranteProdutoUseCase consultarUmRestauranteProdutoUseCase,
                                        ConsultarUmRestauranteUseCase consultarUmRestauranteUseCase) {

        this.cadastrarRestauranteProdutoUseCase = cadastrarRestauranteProdutoUseCase;
        this.atualizarRestauranteProdutoUseCase = atualizarRestauranteProdutoUseCase;
        this.removerRestauranteProdutoUseCase = removerRestauranteProdutoUseCase;
        this.consultarTodosRestauranteProdutoUseCase = consultarTodosRestauranteProdutoUseCase;
        this.consultarUmRestauranteProdutoUseCase = consultarUmRestauranteProdutoUseCase;
        this.consultarUmRestauranteUseCase = consultarUmRestauranteUseCase;
    }


    @PostMapping
    @Operation(summary = "Cadastra um novo produto")
    public ResponseEntity<RestauranteProduto> create(
            @RequestBody RestauranteProdutoRequestDTO restauranteProdutoRequestDTO) {

        logger.info("Iniciando cadastro de produto: {}",
                    restauranteProdutoRequestDTO.getIdRestaurante());

        Restaurante restaurante = consultarUmRestauranteUseCase.execute(restauranteProdutoRequestDTO.getIdRestaurante())
                .orElseThrow(() -> new RuntimeException("Restaurante não encontrado"));

        RestauranteProduto restauranteProduto = new RestauranteProduto();

        restauranteProduto.setRestaurante(restaurante);
        restauranteProduto.setNome(restauranteProdutoRequestDTO.getNome());
        restauranteProduto.setDescricao(restauranteProdutoRequestDTO.getDescricao());
        restauranteProduto.setValor(restauranteProdutoRequestDTO.getValor());
        restauranteProduto.setImagem(restauranteProdutoRequestDTO.getImagem());
        restauranteProduto.setPermiteEntrega(restauranteProdutoRequestDTO.getPermiteEntrega());
        restauranteProduto.setDataAtualizacao(LocalDateTime.now());
        restauranteProduto.setDataInclusao(LocalDateTime.now());

        cadastrarRestauranteProdutoUseCase.execute(restauranteProduto);

        logger.info("Produto cadastrado com sucesso: {}", restauranteProduto.getRestaurante().getId());

        return ResponseEntity.status(201).build();
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza um produto pelo ID do produto")
    public ResponseEntity<RestauranteProduto> update(
            @RequestBody RestauranteProdutoRequestDTO restauranteProdutoRequestDTO, @PathVariable Long id) {

        logger.info("Iniciando atualização do produto com ID: {}", id);

        RestauranteProduto restauranteProduto = consultarUmRestauranteProdutoUseCase.execute(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        Restaurante restaurante = consultarUmRestauranteUseCase.execute(restauranteProdutoRequestDTO.getIdRestaurante())
                .orElseThrow(() -> new RuntimeException("Restaurante não encontrado"));

        restauranteProduto.setRestaurante(restaurante);
        restauranteProduto.setNome(restauranteProdutoRequestDTO.getNome());
        restauranteProduto.setDescricao(restauranteProdutoRequestDTO.getDescricao());
        restauranteProduto.setValor(restauranteProdutoRequestDTO.getValor());
        restauranteProduto.setImagem(restauranteProdutoRequestDTO.getImagem());
        restauranteProduto.setPermiteEntrega(restauranteProdutoRequestDTO.getPermiteEntrega());
        restauranteProduto.setDataAtualizacao(LocalDateTime.now());

        atualizarRestauranteProdutoUseCase.execute(restauranteProduto);

        logger.info("Produto atualizado com sucesso: {}", restauranteProduto.getId());

        return ResponseEntity.status(200).build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remove um produto pelo ID do produto")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {

        logger.info("Iniciando remoção do produto com ID: {}", id);

        removerRestauranteProdutoUseCase.execute(id);

        logger.info("Produto removido com sucesso: {}", id);

        return ResponseEntity.ok().build();
    }

    @GetMapping
    @Operation(summary = "Consulta todos os produtos")
    public ResponseEntity<List<RestauranteProduto>> findAll(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "15") int size) {

        logger.info("Iniciando consulta de todos os produtos. Página: {}, Tamanho: {}",
                    page,
                    size);

        Pageable pageable = PageRequest.of(page - 1, size);
        List<RestauranteProduto> restauranteProdutos = consultarTodosRestauranteProdutoUseCase.execute(
                pageable);

        logger.info(
                "Consulta de todos os produtos concluída. Total de produtos encontrados: {}",
                restauranteProdutos.size());

        return ResponseEntity.ok(restauranteProdutos);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Consulta um produto pelo ID do produto")
    public ResponseEntity<Optional<RestauranteProduto>> findById(@PathVariable Long id) {

        logger.info("Iniciando consulta do produto com ID: {}", id);

        Optional<RestauranteProduto> restauranteProduto = consultarUmRestauranteProdutoUseCase.execute(
                id);

        if (restauranteProduto.isPresent()) {
            logger.info("Produto encontrado: {}", restauranteProduto.get().getId());
        }
        else {
            logger.warn("Produto com ID {} não encontrado", id);
        }

        return ResponseEntity.ok(restauranteProduto);
    }
}
