package br.com.fiap.munchbox.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Builder
@Table(name = "proprietario_restaurante")
public class ProprietarioRestaurante
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_proprietario_restaurante", unique = true, nullable = false)
    private Long idProprietarioRestaurante;

    @ManyToOne
    @JoinColumn(name = "id_proprietario", nullable = false)
    Proprietario proprietario;

    @ManyToOne
    @JoinColumn(name = "id_restaurante", nullable = false)
    Restaurante restaurante;

    @Column(name = "data_atualizacao", nullable = false)
    private LocalDateTime dataAtualizacao;

    @Column(name = "data_inclusao", nullable = false)
    private LocalDateTime dataInclusao;
}
