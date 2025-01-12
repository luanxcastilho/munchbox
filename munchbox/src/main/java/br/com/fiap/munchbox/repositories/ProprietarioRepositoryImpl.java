package br.com.fiap.munchbox.repositories;

import br.com.fiap.munchbox.dtos.proprietario.ProprietarioRequestDTO;
import br.com.fiap.munchbox.entities.Proprietario;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public class ProprietarioRepositoryImpl implements ProprietarioRepository {

    private final JdbcClient jdbcClient;

    public ProprietarioRepositoryImpl(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    @Override
    public Optional<Proprietario> findById(Long id) {
        String sql = "SELECT a.id_proprietario, a.id_usuario, a.nome, a.email, a.celular, a.data_nascimento, a.data_atualizacao, a.data_inclusao FROM proprietario a WHERE a.id_proprietario = :id_proprietario";
        return this.jdbcClient
                .sql(sql)
                .param("id_proprietario", id)
                .query(Proprietario.class)
                .optional();
    }

    @Override
    public List<Proprietario> findAll(int size, int offset) {
        String sql = "SELECT a.id_proprietario, a.id_usuario, a.nome, a.email, a.celular, a.data_nascimento, a.data_atualizacao, a.data_inclusao FROM proprietario a LIMIT :size OFFSET :offset";
        return this.jdbcClient
                .sql(sql)
                .param("size", size)
                .param("offset", offset)
                .query(Proprietario.class)
                .list();
    }

    @Override
    public Integer create(ProprietarioRequestDTO proprietario) {
        String sql = "INSERT INTO proprietario ( id_usuario, nome, email, celular, data_nascimento, data_atualizacao, data_inclusao ) VALUES ( :id_usuario, :nome, :email, :celular, :data_nascimento, :data_atualizacao, :data_inclusao )";
        return this.jdbcClient
                .sql(sql)
                .param("id_usuario", proprietario.getIdUsuario())
                .param("nome", proprietario.getNome())
                .param("email", proprietario.getEmail())
                .param("celular", proprietario.getCelular())
                .param("data_nascimento", proprietario.getDataNascimento())
                .param("data_atualizacao", LocalDateTime.now())
                .param("data_inclusao", LocalDateTime.now())
                .update();
    }

    @Override
    public Integer update(ProprietarioRequestDTO proprietario, Long id) {
        String sql = "UPDATE proprietario SET id_usuario = :id_usuario, nome = :nome, email = :email, celular = :celular, data_nascimento = :data_nascimento, data_atualizacao = :data_atualizacao WHERE id_proprietario = :id_proprietario";
        return this.jdbcClient
                .sql(sql)
                .param("id_usuario", proprietario.getIdUsuario())
                .param("id_proprietario", id)
                .param("nome", proprietario.getNome())
                .param("email", proprietario.getEmail())
                .param("celular", proprietario.getCelular())
                .param("data_nascimento", proprietario.getDataNascimento())
                .param("data_atualizacao", LocalDateTime.now())
                .update();
    }

    @Override
    public Integer delete(Long id) {
        String sql = "DELETE FROM proprietario WHERE id_proprietario = :id_proprietario";
        return this.jdbcClient
                .sql(sql)
                .param("id_proprietario", id)
                .update();
    }

}
