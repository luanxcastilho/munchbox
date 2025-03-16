package br.com.fiap.munchbox.usecase.clienteendereco;

import br.com.fiap.munchbox.domain.core.ClienteEndereco;
import br.com.fiap.munchbox.domain.gateway.ClienteEnderecoGateway;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ConsultarTodosClienteEnderecoUseCase
{
    private final ClienteEnderecoGateway clienteEnderecoGateway;

    public ConsultarTodosClienteEnderecoUseCase(ClienteEnderecoGateway clienteEnderecoGateway)
    {
        this.clienteEnderecoGateway = clienteEnderecoGateway;
    }

    public List<ClienteEndereco> execute(Pageable pageable)
    {
        return this.clienteEnderecoGateway.findAll(pageable);
    }
}
