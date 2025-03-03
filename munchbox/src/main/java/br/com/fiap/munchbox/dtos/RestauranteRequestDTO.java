package br.com.fiap.munchbox.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RestauranteRequestDTO
{
    @NotBlank(message = "O nome é obrigatório")
    String nome;

    @NotBlank(message = "O tipo de cozinha é obrigatório")
    private Long idRestauranteTipoCozinha;

    @NotBlank(message = "A rua é obrigatória")
    private String rua;

    @NotBlank(message = "O número é obrigatório")
    private String numero;

    private String complemento;

    @NotBlank(message = "O bairro é obrigatório")
    private String bairro;

    @NotBlank(message = "A cidade é obrigatória")
    private String cidade;

    @NotBlank(message = "O estado é obrigatório")
    private String estado;

    @NotBlank(message = "O cep é obrigatório")
    private String cep;
}
