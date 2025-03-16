package br.com.fiap.munchbox.usecase.proprietario;

import br.com.fiap.munchbox.domain.gateway.ProprietarioGateway;
import org.springframework.stereotype.Component;

@Component
public class RemoverProprietarioUseCase
{
    private final ProprietarioGateway proprietarioGateway;

    public RemoverProprietarioUseCase(ProprietarioGateway proprietarioGateway)
    {
        this.proprietarioGateway = proprietarioGateway;
    }

    public void execute(Long id)
    {
        this.proprietarioGateway.delete(id);
    }
}
