package br.com.fiap.munchbox.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "usuario_perfil")
public class UsuarioPerfil implements Serializable
{
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario_perfil", unique = true, nullable = false)
    private Long idUsuarioPerfil;

    @Column(name = "nome", length = 50, nullable = false)
    private String nome;

    @Column(name = "data_atualizacao", nullable = false)
    private LocalDateTime dataAtualizacao;

    @Column(name = "data_inclusao", nullable = false)
    private LocalDateTime dataInclusao;


    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "usuarioPerfil", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Usuario> usuario = new HashSet<>();

    public UsuarioPerfil()
    {

    }

    public UsuarioPerfil(String nome, LocalDateTime dataAtualizacao, LocalDateTime dataInclusao)
    {
        this.nome = nome;
        this.dataAtualizacao = dataAtualizacao;
        this.dataInclusao = dataInclusao;
    }
}
