package br.com.fiap.munchbox.entities;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ClienteEndereco {

    private Long idClienteEndereco;
    private Long idCliente;
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
