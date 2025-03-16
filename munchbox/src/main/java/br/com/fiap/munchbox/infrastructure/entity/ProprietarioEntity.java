package br.com.fiap.munchbox.infrastructure.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Builder
@Table(name = "proprietario")
public class ProprietarioEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_proprietario", nullable = false)
    private Long id;

    @NotNull
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_usuario", nullable = false)
    private UsuarioEntity usuarioEntity;

    @Size(max = 50)
    @NotNull
    @Column(name = "nome", nullable = false, length = 50)
    private String nome;

    @NotNull
    @Column(name = "data_nascimento", nullable = false)
    private LocalDate dataNascimento;

    @Size(max = 20)
    @NotNull
    @Column(name = "celular", nullable = false, length = 20)
    private String celular;

    @Size(max = 50)
    @NotNull
    @Column(name = "email", nullable = false, length = 50)
    private String email;

    @NotNull
    @Column(name = "data_atualizacao", nullable = false)
    private LocalDateTime dataAtualizacao;

    @NotNull
    @Column(name = "data_inclusao", nullable = false)
    private LocalDateTime dataInclusao;

}
