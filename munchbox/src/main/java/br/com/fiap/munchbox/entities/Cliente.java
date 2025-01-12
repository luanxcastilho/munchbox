package br.com.fiap.munchbox.entities;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Cliente {

    private Long idCliente;
    private Long idUsuario;
    private String nome;
    private String email;
    private String celular;
    private LocalDate dataNascimento;
    private LocalDateTime dataAtualizacao;
    private LocalDateTime dataInclusao;

}
