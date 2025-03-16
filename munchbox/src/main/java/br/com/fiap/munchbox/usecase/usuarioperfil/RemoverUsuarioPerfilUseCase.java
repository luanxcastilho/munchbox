package br.com.fiap.munchbox.usecase.usuarioperfil;

import br.com.fiap.munchbox.domain.gateway.UsuarioPerfilGateway;
import org.springframework.stereotype.Component;

@Component
public class RemoverUsuarioPerfilUseCase
{
    private final UsuarioPerfilGateway usuarioPerfilGateway;

    public RemoverUsuarioPerfilUseCase(UsuarioPerfilGateway usuarioPerfilGateway)
    {
        this.usuarioPerfilGateway = usuarioPerfilGateway;
    }

    public void execute(Long id)
    {
        this.usuarioPerfilGateway.delete(id);
    }
}
