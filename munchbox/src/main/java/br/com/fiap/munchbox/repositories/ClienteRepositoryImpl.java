package br.com.fiap.munchbox.repositories;

import br.com.fiap.munchbox.dtos.cliente.ClienteRequestDTO;
import br.com.fiap.munchbox.entities.Cliente;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public class ClienteRepositoryImpl implements ClienteRepository {

    private final JdbcClient jdbcClient;

    public ClienteRepositoryImpl(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    @Override
    public Optional<Cliente> findById(Long id) {
        String sql = "SELECT a.id_cliente, a.id_usuario, a.nome, a.email, a.celular, a.data_nascimento, a.data_atualizacao, a.data_inclusao FROM cliente a WHERE a.id_cliente = :id_cliente";
        return this.jdbcClient
                .sql(sql)
                .param("id_cliente", id)
                .query(Cliente.class)
                .optional();
    }

    @Override
    public List<Cliente> findAll(int size, int offset) {
        String sql = "SELECT a.id_cliente, a.id_usuario, a.nome, a.email, a.celular, a.data_nascimento, a.data_atualizacao, a.data_inclusao FROM CLIENTE a LIMIT :size OFFSET :offset";
        return this.jdbcClient
                .sql(sql)
                .param("size", size)
                .param("offset", offset)
                .query(Cliente.class)
                .list();
    }

    @Override
    public Integer create(ClienteRequestDTO cliente) {
        String sql = "INSERT INTO cliente ( id_usuario, nome, email, celular, data_nascimento, data_atualizacao, data_inclusao ) VALUES ( :id_usuario, :nome, :email, :celular, :data_nascimento, :data_atualizacao, :data_inclusao )";
        return this.jdbcClient
                .sql(sql)
                .param("id_usuario", cliente.getIdUsuario())
                .param("nome", cliente.getNome())
                .param("email", cliente.getEmail())
                .param("celular", cliente.getCelular())
                .param("data_nascimento", cliente.getDataNascimento())
                .param("data_atualizacao", LocalDateTime.now())
                .param("data_inclusao", LocalDateTime.now())
                .update();
    }

    @Override
    public Integer update(ClienteRequestDTO cliente, Long id) {
        String sql = "UPDATE cliente SET id_usuario = :id_usuario, nome = :nome, email = :email, celular = :celular, data_nascimento = :data_nascimento, data_atualizacao = :data_atualizacao WHERE id_cliente = :id_cliente";
        return this.jdbcClient
                .sql(sql)
                .param("id_usuario", cliente.getIdUsuario())
                .param("id_cliente", id)
                .param("nome", cliente.getNome())
                .param("email", cliente.getEmail())
                .param("celular", cliente.getCelular())
                .param("data_nascimento", cliente.getDataNascimento())
                .param("data_atualizacao", LocalDateTime.now())
                .update();
    }

    @Override
    public Integer delete(Long id) {
        String sql = "DELETE FROM cliente WHERE id_cliente = :id_cliente";
        return this.jdbcClient
                .sql(sql)
                .param("id_cliente", id)
                .update();
    }

}
