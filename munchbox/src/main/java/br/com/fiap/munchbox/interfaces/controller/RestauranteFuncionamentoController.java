package br.com.fiap.munchbox.interfaces.controller;

import br.com.fiap.munchbox.domain.core.Restaurante;
import br.com.fiap.munchbox.domain.core.RestauranteFuncionamento;
import br.com.fiap.munchbox.interfaces.dto.RestauranteFuncionamentoRequestDTO;
import br.com.fiap.munchbox.usecase.restaurante.ConsultarUmRestauranteUseCase;
import br.com.fiap.munchbox.usecase.restaurantefuncionamento.*;
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
@RequestMapping("/v1/restaurantes-funcionamentos")
@Tag(name = "Horários de funcionamento de restaurantes",
     description = "Gerenciamento de horários de funcionamentos de restaurantes")
public class RestauranteFuncionamentoController {

    private final Logger logger = LoggerFactory.getLogger(RestauranteFuncionamentoController.class);

    private final CadastrarRestauranteFuncionamentoUseCase cadastrarRestauranteFuncionamentoUseCase;
    private final AtualizarRestauranteFuncionamentoUseCase atualizarRestauranteFuncionamentoUseCase;
    private final RemoverRestauranteFuncionamentoUseCase removerRestauranteFuncionamentoUseCase;
    private final ConsultarTodosRestauranteFuncionamentoUseCase consultarTodosRestauranteFuncionamentoUseCase;
    private final ConsultarUmRestauranteFuncionamentoUseCase consultarUmRestauranteFuncionamentoUseCase;
    private final ConsultarUmRestauranteUseCase consultarUmRestauranteUseCase;

    public RestauranteFuncionamentoController(CadastrarRestauranteFuncionamentoUseCase cadastrarRestauranteFuncionamentoUseCase,
                                              AtualizarRestauranteFuncionamentoUseCase atualizarRestauranteFuncionamentoUseCase,
                                              RemoverRestauranteFuncionamentoUseCase removerRestauranteFuncionamentoUseCase,
                                              ConsultarTodosRestauranteFuncionamentoUseCase consultarTodosRestauranteFuncionamentoUseCase,
                                              ConsultarUmRestauranteFuncionamentoUseCase consultarUmRestauranteFuncionamentoUseCase,
                                              ConsultarUmRestauranteUseCase consultarUmRestauranteUseCase) {

        this.cadastrarRestauranteFuncionamentoUseCase = cadastrarRestauranteFuncionamentoUseCase;
        this.atualizarRestauranteFuncionamentoUseCase = atualizarRestauranteFuncionamentoUseCase;
        this.removerRestauranteFuncionamentoUseCase = removerRestauranteFuncionamentoUseCase;
        this.consultarTodosRestauranteFuncionamentoUseCase = consultarTodosRestauranteFuncionamentoUseCase;
        this.consultarUmRestauranteFuncionamentoUseCase = consultarUmRestauranteFuncionamentoUseCase;
        this.consultarUmRestauranteUseCase = consultarUmRestauranteUseCase;
    }


    @PostMapping
    @Operation(summary = "Cadastra um novo horário de funcionamento")
    public ResponseEntity<RestauranteFuncionamento> create(
            @RequestBody RestauranteFuncionamentoRequestDTO restauranteFuncionamentoRequestDTO) {

        logger.info("Iniciando cadastro de horário de funcionamento: {}",
                    restauranteFuncionamentoRequestDTO.getIdRestaurante());

        Restaurante restaurante = consultarUmRestauranteUseCase.execute(restauranteFuncionamentoRequestDTO.getIdRestaurante())
                .orElseThrow(() -> new RuntimeException("Restaurante não encontrado"));

        RestauranteFuncionamento restauranteFuncionamento = new RestauranteFuncionamento();

        restauranteFuncionamento.setRestaurante(restaurante);
        restauranteFuncionamento.setDiaDaSemana(restauranteFuncionamentoRequestDTO.getDiaDaSemana());
        restauranteFuncionamento.setHorarioAbertura(restauranteFuncionamentoRequestDTO.getHorarioAbertura());
        restauranteFuncionamento.setHorarioFechamento(restauranteFuncionamentoRequestDTO.getHorarioFechamento());
        restauranteFuncionamento.setDataAtualizacao(LocalDateTime.now());
        restauranteFuncionamento.setDataInclusao(LocalDateTime.now());

        cadastrarRestauranteFuncionamentoUseCase.execute(restauranteFuncionamento);

        logger.info("Horário de funcionamento cadastrado com sucesso: {}",
                    restauranteFuncionamento.getRestaurante().getId());

        return ResponseEntity.status(201).build();
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza um horário de funcionamento pelo ID do horário de funcionamento")
    public ResponseEntity<RestauranteFuncionamento> update(
            @RequestBody RestauranteFuncionamentoRequestDTO restauranteFuncionamentoRequestDTO, @PathVariable Long id) {

        logger.info("Iniciando atualização do horário de funcionamento com ID: {}", id);

        Restaurante restaurante = consultarUmRestauranteUseCase.execute(restauranteFuncionamentoRequestDTO.getIdRestaurante())
                .orElseThrow(() -> new RuntimeException("Restaurante não encontrado"));

        RestauranteFuncionamento restauranteFuncionamento = consultarUmRestauranteFuncionamentoUseCase.execute(id)
                .orElseThrow(() -> new RuntimeException("Horário de funcionamento não encontrado"));

        restauranteFuncionamento.setId(id);
        restauranteFuncionamento.setRestaurante(restaurante);
        restauranteFuncionamento.setDiaDaSemana(restauranteFuncionamentoRequestDTO.getDiaDaSemana());
        restauranteFuncionamento.setHorarioAbertura(restauranteFuncionamentoRequestDTO.getHorarioAbertura());
        restauranteFuncionamento.setHorarioFechamento(restauranteFuncionamentoRequestDTO.getHorarioFechamento());
        restauranteFuncionamento.setDataAtualizacao(LocalDateTime.now());
        restauranteFuncionamento.setDataInclusao(LocalDateTime.now());

        atualizarRestauranteFuncionamentoUseCase.execute(restauranteFuncionamento);

        logger.info("Horário de funcionamento atualizado com sucesso: {}", restauranteFuncionamento.getId());

        return ResponseEntity.status(200).build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remove um horário de funcionamento pelo ID do horário de funcionamento")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {

        logger.info("Iniciando remoção do horário de funcionamento com ID: {}", id);

        removerRestauranteFuncionamentoUseCase.execute(id);

        logger.info("Horário de funcionamento removido com sucesso: {}", id);

        return ResponseEntity.ok().build();
    }

    @GetMapping
    @Operation(summary = "Consulta todos os horários de funcionamento")
    public ResponseEntity<List<RestauranteFuncionamento>> findAll(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "15") int size) {

        logger.info("Iniciando consulta de todos os horários de funcionamento. Página: {}, Tamanho: {}", page, size);

        Pageable pageable = PageRequest.of(page - 1, size);
        List<RestauranteFuncionamento> restauranteFuncionamentos = consultarTodosRestauranteFuncionamentoUseCase.execute(
                pageable);

        logger.info(
                "Consulta de todos os horários de funcionamento concluída. Total de horários de funcionamento encontrados: {}",
                restauranteFuncionamentos.size());

        return ResponseEntity.ok(restauranteFuncionamentos);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Consulta um horário de funcionamento pelo ID do horário de funcionamento")
    public ResponseEntity<Optional<RestauranteFuncionamento>> findById(@PathVariable Long id) {

        logger.info("Iniciando consulta do horário de funcionamento com ID: {}", id);

        Optional<RestauranteFuncionamento> restauranteFuncionamento = consultarUmRestauranteFuncionamentoUseCase.execute(
                id);

        if (restauranteFuncionamento.isPresent()) {
            logger.info("Horário de funcionamento encontrado: {}", restauranteFuncionamento.get().getId());
        }
        else {
            logger.warn("Horário de funcionamento com ID {} não encontrado", id);
        }

        return ResponseEntity.ok(restauranteFuncionamento);
    }
}
