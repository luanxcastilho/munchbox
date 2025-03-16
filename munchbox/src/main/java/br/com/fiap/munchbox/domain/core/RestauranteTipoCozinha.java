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
public class RestauranteTipoCozinha
{
    private Long id;
    private String nome;
    private LocalDateTime dataAtualizacao;
    private LocalDateTime dataInclusao;
}
