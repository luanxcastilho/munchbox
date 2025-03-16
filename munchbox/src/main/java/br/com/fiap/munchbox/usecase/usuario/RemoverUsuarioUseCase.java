package br.com.fiap.munchbox.usecase.usuario;

import br.com.fiap.munchbox.domain.gateway.UsuarioGateway;
import org.springframework.stereotype.Component;

@Component
public class RemoverUsuarioUseCase
{
    private final UsuarioGateway usuarioGateway;

    public RemoverUsuarioUseCase(UsuarioGateway usuarioGateway)
    {
        this.usuarioGateway = usuarioGateway;
    }

    public void execute(Long id)
    {
        this.usuarioGateway.delete(id);
    }
}
