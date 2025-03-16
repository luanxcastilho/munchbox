package br.com.fiap.munchbox.usecase.cliente;

import br.com.fiap.munchbox.domain.core.Cliente;
import br.com.fiap.munchbox.domain.gateway.ClienteGateway;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ConsultarTodosClienteUseCase
{
    private final ClienteGateway clienteGateway;

    public ConsultarTodosClienteUseCase(ClienteGateway clienteGateway)
    {
        this.clienteGateway = clienteGateway;
    }

    public List<Cliente> execute(Pageable pageable)
    {
        return this.clienteGateway.findAll(pageable);
    }
}
