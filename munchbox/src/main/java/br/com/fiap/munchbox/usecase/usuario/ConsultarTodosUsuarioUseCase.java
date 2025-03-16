package br.com.fiap.munchbox.usecase.usuario;

import br.com.fiap.munchbox.domain.core.Usuario;
import br.com.fiap.munchbox.domain.gateway.UsuarioGateway;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ConsultarTodosUsuarioUseCase
{
    private final UsuarioGateway usuarioGateway;

    public ConsultarTodosUsuarioUseCase(UsuarioGateway usuarioGateway)
    {
        this.usuarioGateway = usuarioGateway;
    }

    public List<Usuario> execute(Pageable pageable)
    {
        return this.usuarioGateway.findAll(pageable);
    }
}
