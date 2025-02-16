package br.com.fiap.munchbox.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "usuario")
public class Usuario implements Serializable
{

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario", unique = true, nullable = false)
    private Long idUsuario;

    @Column(name = "login", length = 50, nullable = false)
    private String login;

    @Column(name = "senha", length = 50, nullable = false)
    private String senha;

    @Column(name = "data_atualizacao", nullable = false)
    private LocalDateTime dataAtualizacao;

    @Column(name = "data_inclusao", nullable = false)
    private LocalDateTime dataInclusao;

    @ManyToOne
    @JoinColumn(name = "id_usuario_perfil")
    private UsuarioPerfil usuarioPerfil;

    public Usuario()
    {

    }

    public Usuario(String login, String senha, LocalDateTime dataAtualizacao, LocalDateTime dataInclusao, UsuarioPerfil usuarioPerfil)
    {
        this.login = login;
        this.senha = senha;
        this.dataAtualizacao = dataAtualizacao;
        this.dataInclusao = dataInclusao;
        this.usuarioPerfil = usuarioPerfil;
    }
}
