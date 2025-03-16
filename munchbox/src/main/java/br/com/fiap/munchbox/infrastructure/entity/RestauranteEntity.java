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
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "restaurante")
public class RestauranteEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_restaurante", nullable = false)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_restaurante_tipo_cozinha", nullable = false)
    private RestauranteTipoCozinhaEntity restauranteTipoCozinhaEntity;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_proprietario", nullable = false)
    private ProprietarioEntity proprietarioEntity;

    @Size(max = 50)
    @NotNull
    @Column(name = "nome", nullable = false, length = 50)
    private String nome;

    @Size(max = 100)
    @NotNull
    @Column(name = "rua", nullable = false, length = 100)
    private String rua;

    @Size(max = 10)
    @NotNull
    @Column(name = "numero", nullable = false, length = 10)
    private String numero;

    @Size(max = 50)
    @Column(name = "complemento", length = 50)
    private String complemento;

    @Size(max = 50)
    @NotNull
    @Column(name = "bairro", nullable = false, length = 50)
    private String bairro;

    @Size(max = 50)
    @NotNull
    @Column(name = "cidade", nullable = false, length = 50)
    private String cidade;

    @Size(max = 50)
    @NotNull
    @Column(name = "estado", nullable = false, length = 50)
    private String estado;

    @Size(max = 10)
    @NotNull
    @Column(name = "cep", nullable = false, length = 10)
    private String cep;

    @NotNull
    @Column(name = "data_atualizacao", nullable = false)
    private LocalDateTime dataAtualizacao;

    @NotNull
    @Column(name = "data_inclusao", nullable = false)
    private LocalDateTime dataInclusao;

}
