package br.com.fiap.munchbox.helpers;

import br.com.fiap.munchbox.entities.Proprietario;

import java.time.LocalDate;
import java.time.LocalDateTime;

public abstract class ProprietarioHelper
{
    public static Proprietario gerarProprietario()
    {
        return Proprietario
                .builder()
                .idProprietario(1L)
                .nome("Proprietario Nome")
                .email("email@gmail.com")
                .celular("11988776655")
                .dataNascimento(LocalDate.of(1993, 2, 21))
                .dataAtualizacao(LocalDateTime.now())
                .dataInclusao(LocalDateTime.now())
                .build();
    }
}
