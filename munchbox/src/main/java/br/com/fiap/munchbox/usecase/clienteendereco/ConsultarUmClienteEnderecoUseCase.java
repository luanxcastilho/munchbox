package br.com.fiap.munchbox.usecase.clienteendereco;

import br.com.fiap.munchbox.domain.core.ClienteEndereco;
import br.com.fiap.munchbox.domain.gateway.ClienteEnderecoGateway;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ConsultarUmClienteEnderecoUseCase
{
    private final ClienteEnderecoGateway clienteEnderecoGateway;

    public ConsultarUmClienteEnderecoUseCase(ClienteEnderecoGateway clienteEnderecoGateway)
    {
        this.clienteEnderecoGateway = clienteEnderecoGateway;
    }

    public Optional<ClienteEndereco> execute(Long id)
    {
        return this.clienteEnderecoGateway.findById(id);
    }
}
