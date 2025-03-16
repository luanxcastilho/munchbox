package br.com.fiap.munchbox.usecase.usuarioperfil;

import br.com.fiap.munchbox.domain.core.UsuarioPerfil;
import br.com.fiap.munchbox.domain.gateway.UsuarioPerfilGateway;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ConsultarTodosUsuarioPerfilUseCase
{
    private final UsuarioPerfilGateway usuarioPerfilGateway;

    public ConsultarTodosUsuarioPerfilUseCase(UsuarioPerfilGateway usuarioPerfilGateway)
    {
        this.usuarioPerfilGateway = usuarioPerfilGateway;
    }

    public List<UsuarioPerfil> execute(Pageable pageable)
    {
        return this.usuarioPerfilGateway.findAll(pageable);
    }
}
