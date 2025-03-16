package br.com.fiap.munchbox.usecase.usuario;

import br.com.fiap.munchbox.domain.core.Usuario;
import br.com.fiap.munchbox.domain.gateway.UsuarioGateway;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ConsultarUmUsuarioUseCase
{
    private final UsuarioGateway usuarioGateway;

    public ConsultarUmUsuarioUseCase(UsuarioGateway usuarioGateway)
    {
        this.usuarioGateway = usuarioGateway;
    }

    public Optional<Usuario> execute(Long id)
    {
        return this.usuarioGateway.findById(id);
    }
}
