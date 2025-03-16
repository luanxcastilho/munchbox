package br.com.fiap.munchbox.domain.core;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Proprietario
{
    private Long id;
    private Usuario usuario;
    private String nome;
    private LocalDate dataNascimento;
    private String celular;
    private String email;
    private LocalDateTime dataAtualizacao;
    private LocalDateTime dataInclusao;
}
