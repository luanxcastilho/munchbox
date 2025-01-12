package br.com.fiap.munchbox.repositories;

import br.com.fiap.munchbox.dtos.proprietarioendereco.ProprietarioEnderecoRequestDTO;
import br.com.fiap.munchbox.entities.ProprietarioEndereco;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public class ProprietarioEnderecoRepositoryImpl implements ProprietarioEnderecoRepository {

    private final JdbcClient jdbcClient;

    public ProprietarioEnderecoRepositoryImpl(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    @Override
    public Optional<ProprietarioEndereco> findById(Long id) {
        String sql = "SELECT a.id_proprietario_endereco, a.id_proprietario, a.rua, a.numero, a.complemento, a.bairro, a.cidade, a.estado, a.cep, a.data_atualizacao, a.data_inclusao " +
                     "  FROM proprietario_endereco a " +
                     " WHERE a.id_proprietario_endereco = :id";

        return this.jdbcClient.sql(sql)
                .param("id", id)
                .query(ProprietarioEndereco.class)
                .optional();
    }

    @Override
    public List<ProprietarioEndereco> findAll(int size, int offset) {
        String sql = "SELECT a.id_proprietario_endereco, a.id_proprietario, a.rua, a.numero, a.complemento, a.bairro, a.cidade, a.estado, a.cep, a.data_atualizacao, a.data_inclusao " +
                     "  FROM proprietario_endereco a " +
                     " LIMIT :size OFFSET :offset";
        return this.jdbcClient
                .sql(sql)
                .param("size", size)
                .param("offset", offset)
                .query(ProprietarioEndereco.class)
                .list();
    }

    @Override
    public Integer create(ProprietarioEnderecoRequestDTO proprietarioEndereco) {
        String sql = "INSERT INTO proprietario_endereco (id_proprietario, rua, numero, complemento, bairro, cidade, estado, cep, data_atualizacao, data_inclusao) " +
                     "VALUES ( :id_proprietario, :rua, :numero, :complemento, :bairro, :cidade, :estado, :cep, :data_atualizacao, :data_inclusao )";

        return this.jdbcClient.sql(sql)
                .param("id_proprietario", proprietarioEndereco.getIdProprietario())
                .param("rua", proprietarioEndereco.getRua())
                .param("numero", proprietarioEndereco.getNumero())
                .param("complemento", proprietarioEndereco.getComplemento())
                .param("bairro", proprietarioEndereco.getBairro())
                .param("cidade", proprietarioEndereco.getCidade())
                .param("estado", proprietarioEndereco.getEstado())
                .param("cep", proprietarioEndereco.getCep())
                .param("data_atualizacao", LocalDateTime.now())
                .param("data_inclusao", LocalDateTime.now())
                .update();
    }

    @Override
    public Integer update(ProprietarioEnderecoRequestDTO proprietarioEndereco, Long id) {
        String sql = "UPDATE proprietario_endereco " +
                     "   SET id_proprietario = :id_proprietario, " +
                     "       rua = :rua, " +
                     "       numero = :numero, " +
                     "       complemento = :complemento, " +
                     "       bairro = :bairro, " +
                     "       cidade = :cidade, " +
                     "       estado = :estado, " +
                     "       cep = :cep, " +
                     "       data_atualizacao = :data_atualizacao " +
                     " WHERE id_proprietario_endereco = :id_proprietario_endereco";

        return this.jdbcClient.sql(sql)
                .param("id_proprietario_endereco", id)
                .param("id_proprietario", proprietarioEndereco.getIdProprietario())
                .param("rua", proprietarioEndereco.getRua())
                .param("numero", proprietarioEndereco.getNumero())
                .param("complemento", proprietarioEndereco.getComplemento())
                .param("bairro", proprietarioEndereco.getBairro())
                .param("cidade", proprietarioEndereco.getCidade())
                .param("estado", proprietarioEndereco.getEstado())
                .param("cep", proprietarioEndereco.getCep())
                .param("data_atualizacao", LocalDateTime.now())
                .update();
    }

    @Override
    public Integer delete(Long id) {
        String sql = "DELETE FROM proprietario_endereco " +
                     " WHERE id_proprietario_endereco = :id_proprietario_endereco";

        return this.jdbcClient.sql(sql)
                .param("id_proprietario_endereco", id)
                .update();
    }

}
