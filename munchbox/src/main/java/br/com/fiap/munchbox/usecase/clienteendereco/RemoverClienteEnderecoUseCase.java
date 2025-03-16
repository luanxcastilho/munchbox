package br.com.fiap.munchbox.usecase.clienteendereco;

import br.com.fiap.munchbox.domain.gateway.ClienteEnderecoGateway;
import org.springframework.stereotype.Component;

@Component
public class RemoverClienteEnderecoUseCase
{
    private final ClienteEnderecoGateway clienteEnderecoGateway;

    public RemoverClienteEnderecoUseCase(ClienteEnderecoGateway clienteEnderecoGateway)
    {
        this.clienteEnderecoGateway = clienteEnderecoGateway;
    }

    public void execute(Long id)
    {
        this.clienteEnderecoGateway.delete(id);
    }
}
