package br.com.fiap.munchbox.usecase.proprietario;

import br.com.fiap.munchbox.domain.core.Proprietario;
import br.com.fiap.munchbox.domain.gateway.ProprietarioGateway;
import org.springframework.stereotype.Component;

@Component
public class AtualizarProprietarioUseCase
{
    private final ProprietarioGateway proprietarioGateway;

    public AtualizarProprietarioUseCase(ProprietarioGateway proprietarioGateway)
    {
        this.proprietarioGateway = proprietarioGateway;
    }

    public void execute(Proprietario proprietario)
    {
        this.proprietarioGateway.update(proprietario);
    }
}
