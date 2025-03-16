package br.com.fiap.munchbox.usecase.proprietario;

import br.com.fiap.munchbox.domain.core.Proprietario;
import br.com.fiap.munchbox.domain.gateway.ProprietarioGateway;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ConsultarUmProprietarioUseCase
{
    private final ProprietarioGateway proprietarioGateway;

    public ConsultarUmProprietarioUseCase(ProprietarioGateway proprietarioGateway)
    {
        this.proprietarioGateway = proprietarioGateway;
    }

    public Optional<Proprietario> execute(Long id)
    {
        return this.proprietarioGateway.findById(id);
    }
}
