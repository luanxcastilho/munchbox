package br.com.fiap.munchbox.usecase.proprietarioendereco;

import br.com.fiap.munchbox.domain.core.ProprietarioEndereco;
import br.com.fiap.munchbox.domain.gateway.ProprietarioEnderecoGateway;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ConsultarUmProprietarioEnderecoUseCase
{
    private final ProprietarioEnderecoGateway proprietarioEnderecoGateway;

    public ConsultarUmProprietarioEnderecoUseCase(ProprietarioEnderecoGateway proprietarioEnderecoGateway)
    {
        this.proprietarioEnderecoGateway = proprietarioEnderecoGateway;
    }

    public Optional<ProprietarioEndereco> execute(Long id)
    {
        return this.proprietarioEnderecoGateway.findById(id);
    }
}
