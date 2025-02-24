package br.com.fiap.munchbox.helpers;

import br.com.fiap.munchbox.entities.Cliente;

import java.time.LocalDate;
import java.time.LocalDateTime;

public abstract class ClienteHelper
{
    public static Cliente gerarCliente()
    {
        return Cliente
                .builder()
                .idCliente(1L)
                .nome("Cliente Nome")
                .email("email@gmail.com")
                .celular("11988776655")
                .dataNascimento(LocalDate.of(1993, 2, 21))
                .dataAtualizacao(LocalDateTime.now())
                .dataInclusao(LocalDateTime.now())
                .build();
    }
}
