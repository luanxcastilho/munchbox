package br.com.fiap.munchbox.usecase.proprietarioendereco;

import br.com.fiap.munchbox.domain.core.ProprietarioEndereco;
import br.com.fiap.munchbox.domain.gateway.ProprietarioEnderecoGateway;
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

class ConsultarTodosProprietarioEnderecoUseCaseTest {

    @Mock
    private ProprietarioEnderecoGateway proprietarioEnderecoGateway;

    @InjectMocks
    private ConsultarTodosProprietarioEnderecoUseCase consultarTodosProprietarioEnderecoUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveConsultarTodosProprietarioEnderecos() {
        Pageable pageable = Pageable.unpaged();
        List<ProprietarioEndereco> expectedList = List.of(new ProprietarioEndereco(), new ProprietarioEndereco());
        when(proprietarioEnderecoGateway.findAll(pageable)).thenReturn(expectedList);

        List<ProprietarioEndereco> result = consultarTodosProprietarioEnderecoUseCase.execute(pageable);

        assertEquals(expectedList, result);
        verify(proprietarioEnderecoGateway).findAll(pageable);
    }
}