package br.com.fiap.munchbox.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RestauranteFuncionamentoRequestDTO
{
    @NotBlank(message = "O dia da semana é obrigatório")
    private int diaDaSemana;

    @NotBlank(message = "O horário de abertura é obrigatório")
    private String horarioAbertura;

    @NotBlank(message = "O horário de fechammento é obrigatório")
    private String horarioFechamento;

    @NotBlank(message = "O id do restaurante é obrigatório")
    private Long idRestaurante;
}
