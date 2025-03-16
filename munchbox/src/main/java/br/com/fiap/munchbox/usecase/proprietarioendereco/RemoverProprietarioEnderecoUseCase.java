package br.com.fiap.munchbox.usecase.proprietarioendereco;

import br.com.fiap.munchbox.domain.gateway.ProprietarioEnderecoGateway;
import org.springframework.stereotype.Component;

@Component
public class RemoverProprietarioEnderecoUseCase
{
    private final ProprietarioEnderecoGateway proprietarioEnderecoGateway;

    public RemoverProprietarioEnderecoUseCase(ProprietarioEnderecoGateway proprietarioEnderecoGateway)
    {
        this.proprietarioEnderecoGateway = proprietarioEnderecoGateway;
    }

    public void execute(Long id)
    {
        this.proprietarioEnderecoGateway.delete(id);
    }
}
