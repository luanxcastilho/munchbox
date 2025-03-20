package br.com.fiap.munchbox.usecase.proprietarioendereco;

import br.com.fiap.munchbox.domain.core.ProprietarioEndereco;
import br.com.fiap.munchbox.domain.gateway.ProprietarioEnderecoGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

class AtualizarProprietarioEnderecoUseCaseTest {

    @Mock
    private ProprietarioEnderecoGateway proprietarioEnderecoGateway;

    @InjectMocks
    private AtualizarProprietarioEnderecoUseCase atualizarProprietarioEnderecoUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveAtualizarProprietarioEndereco() {
        ProprietarioEndereco proprietarioEndereco = new ProprietarioEndereco();

        atualizarProprietarioEnderecoUseCase.execute(proprietarioEndereco);

        verify(proprietarioEnderecoGateway).update(proprietarioEndereco);
    }
}