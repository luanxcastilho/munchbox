package br.com.fiap.munchbox.usecase.proprietario;

import br.com.fiap.munchbox.domain.core.Proprietario;
import br.com.fiap.munchbox.domain.gateway.ProprietarioGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

class CadastrarProprietarioUseCaseTest {

    @Mock
    private ProprietarioGateway proprietarioGateway;

    @InjectMocks
    private CadastrarProprietarioUseCase cadastrarProprietarioUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveCadastrarProprietario() {
        Proprietario proprietario = new Proprietario();

        cadastrarProprietarioUseCase.execute(proprietario);

        verify(proprietarioGateway).create(proprietario);
    }
}