package br.com.fiap.munchbox.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Builder
@Table(name = "proprietario")
public class Proprietario implements Serializable
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_proprietario", unique = true, nullable = false)
    private Long idProprietario;

    @Column(name = "nome", length = 50, nullable = false)
    private String nome;

    @Column(name = "email", length = 50, nullable = false)
    private String email;

    @Column(name = "celular", length = 20, nullable = false)
    private String celular;

    @Column(name = "data_nascimento", nullable = false)
    private LocalDate dataNascimento;

    @Column(name = "data_atualizacao", nullable = false)
    private LocalDateTime dataAtualizacao;

    @Column(name = "data_inclusao", nullable = false)
    private LocalDateTime dataInclusao;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "proprietario", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<ProprietarioEndereco> proprietarioEndereco = new HashSet<>();

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    public Proprietario(Usuario usuario, String nome, String email, String celular, LocalDate dataNascimento, LocalDateTime dataAtualizacao, LocalDateTime dataInclusao)
    {
        this.usuario = usuario;
        this.nome = nome;
        this.email = email;
        this.celular = celular;
        this.dataNascimento = dataNascimento;
        this.dataAtualizacao = dataAtualizacao;
        this.dataInclusao = dataInclusao;
    }

}
