package br.com.fiap.munchbox.usecase.proprietario;

import br.com.fiap.munchbox.domain.core.Proprietario;
import br.com.fiap.munchbox.domain.gateway.ProprietarioGateway;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ConsultarTodosProprietarioUseCase
{
    private final ProprietarioGateway proprietarioGateway;

    public ConsultarTodosProprietarioUseCase(ProprietarioGateway proprietarioGateway)
    {
        this.proprietarioGateway = proprietarioGateway;
    }

    public List<Proprietario> execute(Pageable pageable)
    {
        return this.proprietarioGateway.findAll(pageable);
    }
}
