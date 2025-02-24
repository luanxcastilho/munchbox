package br.com.fiap.munchbox.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Builder
@Table(name = "cliente_endereco")
public class ClienteEndereco implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente_endereco", unique = true, nullable = false)
    private Long idClienteEndereco;

    @Column(name = "rua", length = 100, nullable = false)
    private String rua;

    @Column(name = "numero", length = 10, nullable = false)
    private String numero;

    @Column(name = "complemento", length = 50, nullable = true)
    private String complemento;

    @Column(name = "bairro", length = 50, nullable = false)
    private String bairro;

    @Column(name = "cidade", length = 50, nullable = false)
    private String cidade;

    @Column(name = "estado", length = 50, nullable = false)
    private String estado;

    @Column(name = "cep", length = 10, nullable = false)
    private String cep;

    @Column(name = "data_atualizacao", nullable = false)
    private LocalDateTime dataAtualizacao;

    @Column(name = "data_inclusao", nullable = false)
    private LocalDateTime dataInclusao;

    @ManyToOne
    @JoinColumn(name = "id_cliente", nullable = false)
    private Cliente cliente;

    public ClienteEndereco(String rua, String numero, String complemento, String bairro, String cidade, String estado, String cep, LocalDateTime dataAtualizacao, LocalDateTime dataInclusao, Cliente cliente)
    {
        this.rua = rua;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.cep = cep;
        this.dataAtualizacao = dataAtualizacao;
        this.dataInclusao = dataInclusao;
        this.cliente = cliente;
    }
}
