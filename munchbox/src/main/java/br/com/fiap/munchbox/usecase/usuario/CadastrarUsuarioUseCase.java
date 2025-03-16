package br.com.fiap.munchbox.usecase.usuario;

import br.com.fiap.munchbox.domain.core.Usuario;
import br.com.fiap.munchbox.domain.gateway.UsuarioGateway;
import org.springframework.stereotype.Component;

@Component
public class CadastrarUsuarioUseCase
{
    private final UsuarioGateway usuarioGateway;

    public CadastrarUsuarioUseCase(UsuarioGateway usuarioGateway)
    {
        this.usuarioGateway = usuarioGateway;
    }

    public void execute(Usuario usuario)
    {
        this.usuarioGateway.create(usuario);
    }
}
