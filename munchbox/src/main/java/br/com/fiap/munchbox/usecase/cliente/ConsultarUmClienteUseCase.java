package br.com.fiap.munchbox.usecase.cliente;

import br.com.fiap.munchbox.domain.core.Cliente;
import br.com.fiap.munchbox.domain.gateway.ClienteGateway;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ConsultarUmClienteUseCase
{
    private final ClienteGateway clienteGateway;

    public ConsultarUmClienteUseCase(ClienteGateway clienteGateway)
    {
        this.clienteGateway = clienteGateway;
    }

    public Optional<Cliente> execute(Long id)
    {
        return this.clienteGateway.findById(id);
    }
}
