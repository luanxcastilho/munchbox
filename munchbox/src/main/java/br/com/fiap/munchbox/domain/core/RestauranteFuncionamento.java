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
public class RestauranteFuncionamento {

    private Long id;
    private Restaurante restaurante;
    private int diaDaSemana;
    private String horarioAbertura;
    private String horarioFechamento;
    private LocalDateTime dataAtualizacao;
    private LocalDateTime dataInclusao;
}
