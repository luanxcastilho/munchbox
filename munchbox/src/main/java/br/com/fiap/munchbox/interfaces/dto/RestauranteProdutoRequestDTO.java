package br.com.fiap.munchbox.interfaces.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RestauranteProdutoRequestDTO {

    private Long idRestaurante;

    private String nome;

    private String descricao;

    private BigDecimal valor;

    private String imagem;

    private String permiteEntrega;
}
