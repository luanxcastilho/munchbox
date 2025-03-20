package br.com.fiap.munchbox.usecase.proprietarioendereco;

import br.com.fiap.munchbox.domain.core.ProprietarioEndereco;
import br.com.fiap.munchbox.domain.gateway.ProprietarioEnderecoGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ConsultarUmProprietarioEnderecoUseCaseTest {

    @Mock
    private ProprietarioEnderecoGateway proprietarioEnderecoGateway;

    @InjectMocks
    private ConsultarUmProprietarioEnderecoUseCase consultarUmProprietarioEnderecoUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveConsultarUmProprietarioEndereco() {
        Long id = 1L;
        ProprietarioEndereco expectedProprietarioEndereco = new ProprietarioEndereco();
        when(proprietarioEnderecoGateway.findById(id)).thenReturn(Optional.of(expectedProprietarioEndereco));

        Optional<ProprietarioEndereco> result = consultarUmProprietarioEnderecoUseCase.execute(id);

        assertEquals(Optional.of(expectedProprietarioEndereco), result);
        verify(proprietarioEnderecoGateway).findById(id);
    }
}
