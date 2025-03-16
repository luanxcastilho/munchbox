package br.com.fiap.munchbox.interfaces.controller;

import br.com.fiap.munchbox.interfaces.dto.ProprietarioEnderecoRequestDTO;
import br.com.fiap.munchbox.usecase.proprietario.ConsultarUmProprietarioUseCase;
import br.com.fiap.munchbox.domain.core.Proprietario;
import br.com.fiap.munchbox.domain.core.ProprietarioEndereco;
import br.com.fiap.munchbox.usecase.proprietarioendereco.*;
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
@RequestMapping("/v1/proprietarios-enderecos")
@Tag(name = "Endereços de proprietários", description = "Gerenciamento de endereços de proprietários")
public class ProprietarioEnderecoController
{
    private final Logger logger = LoggerFactory.getLogger(ProprietarioEnderecoController.class);

    private final CadastrarProprietarioEnderecoUseCase cadastrarProprietarioEnderecoUseCase;
    private final AtualizarProprietarioEnderecoUseCase atualizarProprietarioEnderecoUseCase;
    private final RemoverProprietarioEnderecoUseCase removerProprietarioEnderecoUseCase;
    private final ConsultarTodosProprietarioEnderecoUseCase consultarTodosProprietarioEnderecoUseCase;
    private final ConsultarUmProprietarioEnderecoUseCase consultarUmProprietarioEnderecoUseCase;
    private final ConsultarUmProprietarioUseCase consultarUmProprietarioUseCase;

    public ProprietarioEnderecoController(CadastrarProprietarioEnderecoUseCase cadastrarProprietarioEnderecoUseCase, AtualizarProprietarioEnderecoUseCase atualizarProprietarioEnderecoUseCase, RemoverProprietarioEnderecoUseCase removerProprietarioEnderecoUseCase, ConsultarTodosProprietarioEnderecoUseCase consultarTodosProprietarioEnderecoUseCase, ConsultarUmProprietarioEnderecoUseCase consultarUmProprietarioEnderecoUseCase, ConsultarUmProprietarioUseCase consultarUmProprietarioUseCase)
    {
        this.cadastrarProprietarioEnderecoUseCase = cadastrarProprietarioEnderecoUseCase;
        this.atualizarProprietarioEnderecoUseCase = atualizarProprietarioEnderecoUseCase;
        this.removerProprietarioEnderecoUseCase = removerProprietarioEnderecoUseCase;
        this.consultarTodosProprietarioEnderecoUseCase = consultarTodosProprietarioEnderecoUseCase;
        this.consultarUmProprietarioEnderecoUseCase = consultarUmProprietarioEnderecoUseCase;
        this.consultarUmProprietarioUseCase = consultarUmProprietarioUseCase;
    }


    @PostMapping
    @Operation(summary = "Cadastra um novo endereço de proprietário")
    public ResponseEntity<ProprietarioEndereco> create(@RequestBody ProprietarioEnderecoRequestDTO proprietarioEnderecoRequestDTO)
    {
        logger.info("Iniciando cadastro de endereço do proprietário: {}", proprietarioEnderecoRequestDTO.getIdProprietario());

        Proprietario proprietario = consultarUmProprietarioUseCase.execute(proprietarioEnderecoRequestDTO.getIdProprietario()).orElseThrow(() -> new RuntimeException("Proprietario não encontrado"));
        ProprietarioEndereco proprietarioEndereco = new ProprietarioEndereco();

        proprietarioEndereco.setProprietario(proprietario);
        proprietarioEndereco.setRua( proprietarioEnderecoRequestDTO.getRua() );
        proprietarioEndereco.setNumero( proprietarioEnderecoRequestDTO.getNumero() );
        proprietarioEndereco.setComplemento( proprietarioEnderecoRequestDTO.getComplemento() );
        proprietarioEndereco.setBairro( proprietarioEnderecoRequestDTO.getBairro() );
        proprietarioEndereco.setCidade( proprietarioEnderecoRequestDTO.getCidade() );
        proprietarioEndereco.setEstado( proprietarioEnderecoRequestDTO.getEstado() );
        proprietarioEndereco.setCep( proprietarioEnderecoRequestDTO.getCep() );
        proprietarioEndereco.setDataAtualizacao( LocalDateTime.now() );
        proprietarioEndereco.setDataInclusao( LocalDateTime.now() );

        cadastrarProprietarioEnderecoUseCase.execute(proprietarioEndereco);

        logger.info("Endereço de proprietário cadastrado com sucesso: {}", proprietario.getId());

        return ResponseEntity.status(201).build();
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza um endereço de proprietário pelo ID do endereço de proprietário")
    public ResponseEntity<ProprietarioEndereco> create(@RequestBody ProprietarioEnderecoRequestDTO proprietarioEnderecoRequestDTO, @PathVariable Long id)
    {
        logger.info("Iniciando atualização do endereço de proprietário com ID: {}", id);

        ProprietarioEndereco proprietarioEndereco = consultarUmProprietarioEnderecoUseCase.execute(id).orElseThrow(() -> new RuntimeException("Endereço de proprietario não encontrado"));
        Proprietario proprietario = consultarUmProprietarioUseCase.execute(proprietarioEnderecoRequestDTO.getIdProprietario()).orElseThrow(() -> new RuntimeException("Proprietario não encontrado"));

        proprietarioEndereco.setProprietario(proprietario);
        proprietarioEndereco.setRua( proprietarioEnderecoRequestDTO.getRua() );
        proprietarioEndereco.setNumero( proprietarioEnderecoRequestDTO.getNumero() );
        proprietarioEndereco.setComplemento( proprietarioEnderecoRequestDTO.getComplemento() );
        proprietarioEndereco.setBairro( proprietarioEnderecoRequestDTO.getBairro() );
        proprietarioEndereco.setCidade( proprietarioEnderecoRequestDTO.getCidade() );
        proprietarioEndereco.setEstado( proprietarioEnderecoRequestDTO.getEstado() );
        proprietarioEndereco.setCep( proprietarioEnderecoRequestDTO.getCep() );
        proprietarioEndereco.setDataAtualizacao( LocalDateTime.now() );
        proprietarioEndereco.setDataInclusao( LocalDateTime.now() );

        atualizarProprietarioEnderecoUseCase.execute(proprietarioEndereco);

        logger.info("Endereço de proprietário atualizado com sucesso: {}", proprietarioEndereco.getId());

        return ResponseEntity.status(200).build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remove um endereço de proprietário pelo ID do endereço de proprietário")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id)
    {
        logger.info("Iniciando remoção do endereço de proprietário com ID: {}", id);

        removerProprietarioEnderecoUseCase.execute(id);

        logger.info("Endereço de proprietário removido com sucesso: {}", id);

        return ResponseEntity.ok().build();
    }

    @GetMapping
    @Operation(summary = "Consulta todos os endereços de proprietários")
    public ResponseEntity<List<ProprietarioEndereco>> findAll(@RequestParam(value = "page", defaultValue = "1") int page, @RequestParam(value = "size", defaultValue = "15") int size)
    {
        logger.info("Iniciando consulta de todos os endereços de proprietários. Página: {}, Tamanho: {}", page, size);

        Pageable pageable = PageRequest.of(page - 1, size);
        List<ProprietarioEndereco> proprietarioEnderecos = consultarTodosProprietarioEnderecoUseCase.execute(pageable);

        logger.info("Consulta de todos os endereços de proprietários concluída. Total de endereços de proprietários encontrados: {}", proprietarioEnderecos.size());

        return ResponseEntity.ok(proprietarioEnderecos);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Consulta um endereço de proprietário pelo ID do endereço de proprietário")
    public ResponseEntity<Optional<ProprietarioEndereco>> findById(@PathVariable Long id)
    {
        logger.info("Iniciando consulta do endereço de proprietário com ID: {}", id);

        Optional<ProprietarioEndereco> proprietarioEndereco = consultarUmProprietarioEnderecoUseCase.execute(id);

        if (proprietarioEndereco.isPresent()) {
            logger.info("Endereço de proprietário encontrado: {}", proprietarioEndereco.get().getId());
        } else {
            logger.warn("Endereço de proprietário com ID {} não encontrado", id);
        }

        return ResponseEntity.ok(proprietarioEndereco);
    }
}
