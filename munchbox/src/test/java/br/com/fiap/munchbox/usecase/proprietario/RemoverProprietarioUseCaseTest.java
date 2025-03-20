package br.com.fiap.munchbox.usecase.proprietario;

import br.com.fiap.munchbox.domain.gateway.ProprietarioGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

class RemoverProprietarioUseCaseTest {

    @Mock
    private ProprietarioGateway proprietarioGateway;

    @InjectMocks
    private RemoverProprietarioUseCase removerProprietarioUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveRemoverProprietario() {
        Long id = 1L;

        removerProprietarioUseCase.execute(id);

        verify(proprietarioGateway).delete(id);
    }
}