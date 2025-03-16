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
public class Restaurante {

    private Long id;
    private RestauranteTipoCozinha restauranteTipoCozinha;
    private Proprietario proprietario;
    private String nome;
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
