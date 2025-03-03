package br.com.fiap.munchbox.entities;

import jakarta.persistence.*;
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
@Table(name = "restaurante_funcionamento")
public class RestauranteFuncionamento implements Serializable
{
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_restaurante_funcionamento", unique = true, nullable = false)
    private Long idRestauranteFuncionamento;

    @Column(name = "dia_semana", nullable = false)
    private int diaDaSemana;

    @Column(name = "horario_abertura", nullable = false)
    private String horarioAbertura;

    @Column(name = "horario_fechamento", nullable = false)
    private String horarioFechamento;

    @Column(name = "data_atualizacao", nullable = false)
    private LocalDateTime dataAtualizacao;

    @Column(name = "data_inclusao", nullable = false)
    private LocalDateTime dataInclusao;

    @ManyToOne
    @JoinColumn(name = "id_restaurante")
    private Restaurante restaurante;
}
