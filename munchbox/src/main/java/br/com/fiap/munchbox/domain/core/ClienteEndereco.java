package br.com.fiap.munchbox.domain.core;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClienteEndereco
{
    private Long id;
    private Cliente cliente;
    private String rua;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String estado;
    private String cep;
    private LocalDateTime dataAtualizacao;
    private LocalDateTime dataInclusao;
}
