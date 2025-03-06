package br.com.fiap.munchbox.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProprietarioRestauranteRequestDTO
{
    @NotNull(message = "O id do restaurante é obrigatório")
    @Positive(message = "ID inválido")
    private Long idRestaurante;

    @NotNull(message = "O id do proprietário é obrigatório")
    @Positive(message = "ID inválido")
    private Long idProprietario;
}
