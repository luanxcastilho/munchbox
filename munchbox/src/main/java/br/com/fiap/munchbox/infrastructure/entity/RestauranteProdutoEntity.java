package br.com.fiap.munchbox.infrastructure.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "restaurante_produto")
public class RestauranteProdutoEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_restaurante_produto", nullable = false)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_restaurante", nullable = false)
    private RestauranteEntity restauranteEntity;

    @Size(max = 50)
    @NotNull
    @Column(name = "nome", nullable = false, length = 50)
    private String nome;

    @Size(max = 250)
    @NotNull
    @Column(name = "descricao", nullable = false)
    private String descricao;

    @NotNull
    @Column(name = "valor", nullable = false)
    private BigDecimal valor;

    @Size(max = 250)
    @Column(name = "imagem")
    private String imagem;

    @Size(max = 1)
    @NotNull
    @Column(name = "flag_permite_entrega", nullable = false)
    private String permiteEntrega;

    @NotNull
    @Column(name = "data_atualizacao", nullable = false)
    private LocalDateTime dataAtualizacao;

    @NotNull
    @Column(name = "data_inclusao", nullable = false)
    private LocalDateTime dataInclusao;

}
