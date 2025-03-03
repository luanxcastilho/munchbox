package br.com.fiap.munchbox.dtos;

import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class ProprietarioRequestDTO {

    @NotNull(message = "O id do usuário é obrigatório")
    @Positive(message = "ID inválido")
    private Long idUsuario;

    @NotBlank(message = "O nome é obrigatório")
    private String nome;

    @NotBlank(message = "O e-mail é obrigatório")
    @Email(message = "E-mail inválido")
    private String email;

    @NotBlank(message = "O celular é obrigatório")
    @Pattern(regexp = "^\\d{2}\\d{9}$", message = "Celular inválido. Formato esperado: DDD + 9 dígitos.")
    private String celular;

    @NotNull(message = "A data de nascimento é obrigatória")
    @Past(message = "A data de nascimento deve ser uma data no passado")
    private LocalDate dataNascimento;

}
