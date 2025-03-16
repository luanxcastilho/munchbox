package br.com.fiap.munchbox.usecase.usuarioperfil;

import br.com.fiap.munchbox.domain.core.UsuarioPerfil;
import br.com.fiap.munchbox.domain.gateway.UsuarioPerfilGateway;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ConsultarUmUsuarioPerfilUseCase
{
    private final UsuarioPerfilGateway usuarioPerfilGateway;

    public ConsultarUmUsuarioPerfilUseCase(UsuarioPerfilGateway usuarioPerfilGateway)
    {
        this.usuarioPerfilGateway = usuarioPerfilGateway;
    }

    public Optional<UsuarioPerfil> execute(Long id)
    {
        return this.usuarioPerfilGateway.findById(id);
    }
}
