package br.com.fiap.munchbox.interfaces.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClienteRequestDTO
{
    private Long idUsuario;
    private String nome;
    private LocalDate dataNascimento;
    private String celular;
    private String email;
}
