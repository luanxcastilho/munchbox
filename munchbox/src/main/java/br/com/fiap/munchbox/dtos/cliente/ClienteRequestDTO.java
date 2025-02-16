package br.com.fiap.munchbox.dtos.cliente;

import lombok.*;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ClienteRequestDTO {

    private String nome;
    private String email;
    private String celular;
    private LocalDate dataNascimento;
    private Long idUsuario;
}
