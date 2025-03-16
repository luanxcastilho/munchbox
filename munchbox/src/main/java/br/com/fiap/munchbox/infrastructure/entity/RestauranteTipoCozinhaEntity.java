package br.com.fiap.munchbox.infrastructure.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "restaurante_tipo_cozinha")
public class RestauranteTipoCozinhaEntity implements Serializable
{
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_restaurante_tipo_cozinha", nullable = false)
    private Long id;

    @Size(max = 50)
    @NotNull
    @Column(name = "nome", nullable = false, length = 50)
    private String nome;

    @NotNull
    @Column(name = "data_atualizacao", nullable = false)
    private LocalDateTime dataAtualizacao;

    @NotNull
    @Column(name = "data_inclusao", nullable = false)
    private LocalDateTime dataInclusao;

}
