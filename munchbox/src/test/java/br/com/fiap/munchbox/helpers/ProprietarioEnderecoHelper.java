package br.com.fiap.munchbox.helpers;

import br.com.fiap.munchbox.entities.ProprietarioEndereco;

import java.time.LocalDateTime;

public abstract class ProprietarioEnderecoHelper
{
    public static ProprietarioEndereco gerarProprietarioEndereco()
    {
        return ProprietarioEndereco
                .builder()
                .idProprietarioEndereco(1L)
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
