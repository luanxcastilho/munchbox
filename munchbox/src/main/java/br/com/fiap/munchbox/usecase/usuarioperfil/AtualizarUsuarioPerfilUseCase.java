package br.com.fiap.munchbox.usecase.usuarioperfil;

import br.com.fiap.munchbox.domain.core.UsuarioPerfil;
import br.com.fiap.munchbox.domain.gateway.UsuarioPerfilGateway;
import org.springframework.stereotype.Component;

@Component
public class AtualizarUsuarioPerfilUseCase
{
    private final UsuarioPerfilGateway usuarioPerfilGateway;

    public AtualizarUsuarioPerfilUseCase(UsuarioPerfilGateway usuarioPerfilGateway)
    {
        this.usuarioPerfilGateway = usuarioPerfilGateway;
    }

    public void execute(UsuarioPerfil usuarioPerfil)
    {
        this.usuarioPerfilGateway.update(usuarioPerfil);
    }
}
