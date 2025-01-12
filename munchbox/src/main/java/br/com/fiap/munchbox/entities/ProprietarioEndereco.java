package br.com.fiap.munchbox.entities;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProprietarioEndereco {

    private Long idProprietarioEndereco;
    private Long idProprietario;
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
