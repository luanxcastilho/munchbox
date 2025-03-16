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
@Table(name = "restaurante_funcionamento")
public class RestauranteFuncionamentoEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_restaurante_funcionamento", nullable = false)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_restaurante", nullable = false)
    private RestauranteEntity restauranteEntity;

    @NotNull
    @Column(name = "dia_semana", nullable = false)
    private Integer diaDaSemana;

    @Size(max = 5)
    @NotNull
    @Column(name = "horario_abertura", nullable = false)
    private String horarioAbertura;

    @Size(max = 5)
    @NotNull
    @Column(name = "horario_fechamento", nullable = false)
    private String horarioFechamento;

    @NotNull
    @Column(name = "data_atualizacao", nullable = false)
    private LocalDateTime dataAtualizacao;

    @NotNull
    @Column(name = "data_inclusao", nullable = false)
    private LocalDateTime dataInclusao;

}
