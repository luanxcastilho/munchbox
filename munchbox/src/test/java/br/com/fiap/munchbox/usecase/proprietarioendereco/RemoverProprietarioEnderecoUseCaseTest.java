package br.com.fiap.munchbox.usecase.proprietarioendereco;

import br.com.fiap.munchbox.domain.gateway.ProprietarioEnderecoGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

class RemoverProprietarioEnderecoUseCaseTest {

    @Mock
    private ProprietarioEnderecoGateway proprietarioEnderecoGateway;

    @InjectMocks
    private RemoverProprietarioEnderecoUseCase removerProprietarioEnderecoUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveRemoverProprietarioEndereco() {
        Long id = 1L;

        removerProprietarioEnderecoUseCase.execute(id);

        verify(proprietarioEnderecoGateway).delete(id);
    }
}
