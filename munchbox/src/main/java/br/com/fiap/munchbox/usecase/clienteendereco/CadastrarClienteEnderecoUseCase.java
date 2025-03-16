package br.com.fiap.munchbox.usecase.clienteendereco;

import br.com.fiap.munchbox.domain.core.ClienteEndereco;
import br.com.fiap.munchbox.domain.gateway.ClienteEnderecoGateway;
import org.springframework.stereotype.Component;

@Component
public class CadastrarClienteEnderecoUseCase
{
    private final ClienteEnderecoGateway clienteEnderecoGateway;

    public CadastrarClienteEnderecoUseCase(ClienteEnderecoGateway clienteEnderecoGateway)
    {
        this.clienteEnderecoGateway = clienteEnderecoGateway;
    }

    public void execute(ClienteEndereco clienteEndereco)
    {
        this.clienteEnderecoGateway.create(clienteEndereco);
    }
}
