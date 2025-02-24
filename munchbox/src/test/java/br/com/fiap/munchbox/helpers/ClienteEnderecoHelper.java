package br.com.fiap.munchbox.helpers;

import br.com.fiap.munchbox.entities.ClienteEndereco;

import java.time.LocalDateTime;

public abstract class ClienteEnderecoHelper
{
    public static ClienteEndereco gerarClienteEndereco()
    {
        return ClienteEndereco
                .builder()
                .idClienteEndereco(1L)
                .rua("rua de teste")
                .numero("123 A")
                .complemento("complemento de teste")
                .bairro("bairro de teste")
                .cidade("cidade de teste")
                .estado("SP")
                .cep("01234-567")
                .dataAtualizacao(LocalDateTime.now())
                .dataInclusao(LocalDateTime.now())
                .build();
    }
}
