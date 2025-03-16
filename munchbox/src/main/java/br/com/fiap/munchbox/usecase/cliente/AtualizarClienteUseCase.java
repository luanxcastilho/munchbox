package br.com.fiap.munchbox.usecase.cliente;

import br.com.fiap.munchbox.domain.core.Cliente;
import br.com.fiap.munchbox.domain.gateway.ClienteGateway;
import org.springframework.stereotype.Component;

@Component
public class AtualizarClienteUseCase
{
    private final ClienteGateway clienteGateway;

    public AtualizarClienteUseCase(ClienteGateway clienteGateway)
    {
        this.clienteGateway = clienteGateway;
    }

    public void execute(Cliente cliente)
    {
        this.clienteGateway.update(cliente);
    }
}
