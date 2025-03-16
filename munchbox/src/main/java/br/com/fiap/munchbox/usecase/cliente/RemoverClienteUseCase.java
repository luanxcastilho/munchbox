package br.com.fiap.munchbox.usecase.cliente;

import br.com.fiap.munchbox.domain.gateway.ClienteGateway;
import org.springframework.stereotype.Component;

@Component
public class RemoverClienteUseCase
{
    private final ClienteGateway clienteGateway;

    public RemoverClienteUseCase(ClienteGateway clienteGateway)
    {
        this.clienteGateway = clienteGateway;
    }

    public void execute(Long id)
    {
        this.clienteGateway.delete(id);
    }
}
