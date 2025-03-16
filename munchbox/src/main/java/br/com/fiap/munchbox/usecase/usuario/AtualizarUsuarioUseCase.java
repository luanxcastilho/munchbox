package br.com.fiap.munchbox.usecase.usuario;

import br.com.fiap.munchbox.domain.core.Usuario;
import br.com.fiap.munchbox.domain.gateway.UsuarioGateway;
import org.springframework.stereotype.Component;

@Component
public class AtualizarUsuarioUseCase
{
    private final UsuarioGateway usuarioGateway;

    public AtualizarUsuarioUseCase(UsuarioGateway usuarioGateway)
    {
        this.usuarioGateway = usuarioGateway;
    }

    public void execute(Usuario usuario)
    {
        this.usuarioGateway.update(usuario);
    }
}
