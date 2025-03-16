package br.com.fiap.munchbox.usecase.proprietarioendereco;

import br.com.fiap.munchbox.domain.core.ProprietarioEndereco;
import br.com.fiap.munchbox.domain.gateway.ProprietarioEnderecoGateway;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ConsultarTodosProprietarioEnderecoUseCase
{
    private final ProprietarioEnderecoGateway proprietarioEnderecoGateway;

    public ConsultarTodosProprietarioEnderecoUseCase(ProprietarioEnderecoGateway proprietarioEnderecoGateway)
    {
        this.proprietarioEnderecoGateway = proprietarioEnderecoGateway;
    }

    public List<ProprietarioEndereco> execute(Pageable pageable)
    {
        return this.proprietarioEnderecoGateway.findAll(pageable);
    }
}
