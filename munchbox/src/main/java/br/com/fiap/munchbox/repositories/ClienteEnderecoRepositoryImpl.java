package br.com.fiap.munchbox.repositories;

import br.com.fiap.munchbox.dtos.clienteendereco.ClienteEnderecoRequestDTO;
import br.com.fiap.munchbox.entities.ClienteEndereco;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public class ClienteEnderecoRepositoryImpl implements ClienteEnderecoRepository {

    private final JdbcClient jdbcClient;

    public ClienteEnderecoRepositoryImpl(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    @Override
    public Optional<ClienteEndereco> findById(Long id) {
        String sql = "SELECT a.id_cliente_endereco, a.id_cliente, a.rua, a.numero, a.complemento, a.bairro, a.cidade, a.estado, a.cep, a.data_atualizacao, a.data_inclusao " +
                     "  FROM cliente_endereco a " +
                     " WHERE a.id_cliente_endereco = :id";

        return this.jdbcClient.sql(sql)
                .param("id", id)
                .query(ClienteEndereco.class)
                .optional();
    }

    @Override
    public List<ClienteEndereco> findAll(int size, int offset) {
        String sql = "SELECT a.id_cliente_endereco, a.id_cliente, a.rua, a.numero, a.complemento, a.bairro, a.cidade, a.estado, a.cep, a.data_atualizacao, a.data_inclusao " +
                     "  FROM cliente_endereco a " +
                     " LIMIT :size OFFSET :offset";
        return this.jdbcClient
                .sql(sql)
                .param("size", size)
                .param("offset", offset)
                .query(ClienteEndereco.class)
                .list();
    }

    @Override
    public Integer create(ClienteEnderecoRequestDTO clienteEndereco) {
        String sql = "INSERT INTO cliente_endereco (id_cliente, rua, numero, complemento, bairro, cidade, estado, cep, data_atualizacao, data_inclusao) " +
                     "VALUES ( :id_cliente, :rua, :numero, :complemento, :bairro, :cidade, :estado, :cep, :data_atualizacao, :data_inclusao )";

        return this.jdbcClient.sql(sql)
                .param("id_cliente", clienteEndereco.getIdCliente())
                .param("rua", clienteEndereco.getRua())
                .param("numero", clienteEndereco.getNumero())
                .param("complemento", clienteEndereco.getComplemento())
                .param("bairro", clienteEndereco.getBairro())
                .param("cidade", clienteEndereco.getCidade())
                .param("estado", clienteEndereco.getEstado())
                .param("cep", clienteEndereco.getCep())
                .param("data_atualizacao", LocalDateTime.now())
                .param("data_inclusao", LocalDateTime.now())
                .update();
    }

    @Override
    public Integer update(ClienteEnderecoRequestDTO clienteEndereco, Long id) {
        String sql = "UPDATE cliente_endereco " +
                     "   SET id_cliente = :id_cliente, " +
                     "       rua = :rua, " +
                     "       numero = :numero, " +
                     "       complemento = :complemento, " +
                     "       bairro = :bairro, " +
                     "       cidade = :cidade, " +
                     "       estado = :estado, " +
                     "       cep = :cep, " +
                     "       data_atualizacao = :data_atualizacao " +
                     " WHERE id_cliente_endereco = :id_cliente_endereco";

        return this.jdbcClient.sql(sql)
                .param("id_cliente_endereco", id)
                .param("id_cliente", clienteEndereco.getIdCliente())
                .param("rua", clienteEndereco.getRua())
                .param("numero", clienteEndereco.getNumero())
                .param("complemento", clienteEndereco.getComplemento())
                .param("bairro", clienteEndereco.getBairro())
                .param("cidade", clienteEndereco.getCidade())
                .param("estado", clienteEndereco.getEstado())
                .param("cep", clienteEndereco.getCep())
                .param("data_atualizacao", LocalDateTime.now())
                .update();
    }

    @Override
    public Integer delete(Long id) {
        String sql = "DELETE FROM cliente_endereco " +
                     " WHERE id_cliente_endereco = :id_cliente_endereco";

        return this.jdbcClient.sql(sql)
                .param("id_cliente_endereco", id)
                .update();
    }

}
