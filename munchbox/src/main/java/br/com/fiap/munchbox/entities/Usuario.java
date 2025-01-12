package br.com.fiap.munchbox.entities;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Usuario {

    private Long idUsuario;
    private String login;
    private String senha;
    private LocalDateTime dataAtualizacao;
    private LocalDateTime dataInclusao;

}
