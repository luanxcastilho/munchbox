package br.com.fiap.munchbox.usecase.proprietarioendereco;

import br.com.fiap.munchbox.domain.core.ProprietarioEndereco;
import br.com.fiap.munchbox.domain.gateway.ProprietarioEnderecoGateway;
import org.springframework.stereotype.Component;

@Component
public class CadastrarProprietarioEnderecoUseCase
{
    private final ProprietarioEnderecoGateway proprietarioEnderecoGateway;

    public CadastrarProprietarioEnderecoUseCase(ProprietarioEnderecoGateway proprietarioEnderecoGateway)
    {
        this.proprietarioEnderecoGateway = proprietarioEnderecoGateway;
    }

    public void execute(ProprietarioEndereco proprietarioEndereco)
    {
        this.proprietarioEnderecoGateway.create(proprietarioEndereco);
    }
}
