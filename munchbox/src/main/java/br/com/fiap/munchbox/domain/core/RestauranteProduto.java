package br.com.fiap.munchbox.domain.core;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RestauranteProduto {

    private Long id;

    private Restaurante restaurante;

    private String nome;

    private String descricao;

    private BigDecimal valor;

    private String imagem;

    private String permiteEntrega;

    private LocalDateTime dataAtualizacao;

    private LocalDateTime dataInclusao;
}
