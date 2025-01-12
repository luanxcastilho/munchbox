package br.com.fiap.munchbox.entities;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Proprietario {

    private Long idProprietario;
    private Long idUsuario;
    private String nome;
    private String email;
    private String celular;
    private LocalDate dataNascimento;
    private LocalDateTime dataAtualizacao;
    private LocalDateTime dataInclusao;

}
