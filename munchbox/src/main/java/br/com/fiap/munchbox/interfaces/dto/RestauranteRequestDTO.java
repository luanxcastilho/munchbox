package br.com.fiap.munchbox.interfaces.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RestauranteRequestDTO {

    String nome;

    private Long idRestauranteTipoCozinha;

    private Long idProprietario;

    private String rua;

    private String numero;

    private String complemento;

    private String bairro;

    private String cidade;

    private String estado;

    private String cep;
}

