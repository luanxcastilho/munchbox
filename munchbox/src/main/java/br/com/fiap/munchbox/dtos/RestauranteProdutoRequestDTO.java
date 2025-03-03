package br.com.fiap.munchbox.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RestauranteProdutoRequestDTO
{
    @NotBlank(message = "O nome é obrigatório")
    private String nome;

    @NotBlank(message = "A descrição é obrigatória")
    private String descricao;

    @NotBlank(message = "O preço é obrigatório")
    private Double preco;

    private String imagem;

    @NotBlank(message = "O flag é obrigatório")
    private String flagPermiteEntrega;

    @NotBlank(message = "O restaurante é obrigatório")
    private Long idRestaurante;
}
