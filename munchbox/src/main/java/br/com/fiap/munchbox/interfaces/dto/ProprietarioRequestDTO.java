package br.com.fiap.munchbox.interfaces.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProprietarioRequestDTO
{
    private Long idUsuario;
    private String nome;
    private LocalDate dataNascimento;
    private String celular;
    private String email;
}
