package br.com.fiap.munchbox.interfaces.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RestauranteFuncionamentoRequestDTO {

    private Long idRestaurante;
    private int diaDaSemana;
    private String horarioAbertura;
    private String horarioFechamento;
}
