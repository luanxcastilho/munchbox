package br.com.fiap.munchbox.usecase.proprietario;

import br.com.fiap.munchbox.domain.core.Proprietario;
import br.com.fiap.munchbox.domain.gateway.ProprietarioGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ConsultarTodosProprietarioUseCaseTest {

    @Mock
    private ProprietarioGateway proprietarioGateway;

    @InjectMocks
    private ConsultarTodosProprietarioUseCase consultarTodosProprietarioUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveConsultarTodosProprietarios() {
        Pageable pageable = Pageable.unpaged();
        List<Proprietario> proprietarios = List.of(new Proprietario(), new Proprietario());

        when(proprietarioGateway.findAll(pageable)).thenReturn(proprietarios);

        List<Proprietario> resultado = consultarTodosProprietarioUseCase.execute(pageable);

        assertEquals(proprietarios, resultado);
        verify(proprietarioGateway).findAll(pageable);
    }
}