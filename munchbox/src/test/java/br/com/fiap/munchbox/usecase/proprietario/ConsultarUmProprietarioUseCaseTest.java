package br.com.fiap.munchbox.usecase.proprietario;

import br.com.fiap.munchbox.domain.core.Proprietario;
import br.com.fiap.munchbox.domain.gateway.ProprietarioGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ConsultarUmProprietarioUseCaseTest {

    @Mock
    private ProprietarioGateway proprietarioGateway;

    @InjectMocks
    private ConsultarUmProprietarioUseCase consultarUmProprietarioUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveConsultarUmProprietario() {
        Long id = 1L;
        Proprietario proprietario = new Proprietario();

        when(proprietarioGateway.findById(id)).thenReturn(Optional.of(proprietario));

        Optional<Proprietario> resultado = consultarUmProprietarioUseCase.execute(id);

        assertTrue(resultado.isPresent());
        assertEquals(proprietario, resultado.get());
        verify(proprietarioGateway).findById(id);
    }
}