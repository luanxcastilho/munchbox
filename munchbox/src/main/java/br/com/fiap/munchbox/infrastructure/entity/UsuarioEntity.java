package br.com.fiap.munchbox.infrastructure.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "usuario")
public class UsuarioEntity implements Serializable
{
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario", unique = true, nullable = false)
    private Long id;

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
    private UsuarioPerfilEntity usuarioPerfilEntity;
}