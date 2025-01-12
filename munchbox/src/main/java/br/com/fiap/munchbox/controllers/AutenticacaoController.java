package br.com.fiap.munchbox.controllers;

import br.com.fiap.munchbox.dtos.usuario.UsuarioRequestDTO;
import br.com.fiap.munchbox.entities.Usuario;
import br.com.fiap.munchbox.services.AutenticacaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/autenticacao")
@Tag(
        name = "Autenticação",
        description = "Serviços referentes a autenticação de usuários"
)
public class AutenticacaoController {

    private final Logger logger = LoggerFactory.getLogger(AutenticacaoController.class);

    private final AutenticacaoService autenticacaoService;

    public AutenticacaoController(AutenticacaoService autenticacaoService) {
        this.autenticacaoService = autenticacaoService;
    }

    @PostMapping("/autenticar")
    @Operation(
            summary = "Autenticação de usuário",
            description = "Realiza a autenticação de um usuário no sistema através de login e senha"
    )
    public ResponseEntity<Usuario> autenticar(
            @RequestBody UsuarioRequestDTO usuarioRequestDTO
    ) {
        logger.info("POST /v1/autenticacao/autenticar BODY: " + usuarioRequestDTO);
        this.autenticacaoService.autenticar(usuarioRequestDTO);
        return ResponseEntity.status(200).build();
    }

}
