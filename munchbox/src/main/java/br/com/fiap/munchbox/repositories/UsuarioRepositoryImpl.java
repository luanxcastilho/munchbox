package br.com.fiap.munchbox.repositories;

import br.com.fiap.munchbox.dtos.usuario.UsuarioRequestDTO;
import br.com.fiap.munchbox.entities.Usuario;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public class UsuarioRepositoryImpl implements UsuarioRepository {
    
    private final JdbcClient jdbcClient;

    public UsuarioRepositoryImpl(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    @Override
    public Optional<Usuario> findById(Long id) {
        String sql = "SELECT a.* FROM usuario a WHERE a.id_usuario = :id";
        return this.jdbcClient
                .sql(sql)
                .param("id", id)
                .query(Usuario.class)
                .optional();
    }

    @Override
    public List<Usuario> findAll(int size, int offset) {
        String sql = "SELECT a.* FROM usuario a LIMIT :size OFFSET :offset";
        return this.jdbcClient
                .sql(sql)
                .param("size", size)
                .param("offset", offset)
                .query(Usuario.class)
                .list();
    }

    @Override
    public Integer create(UsuarioRequestDTO usuario) {
        String sql = "INSERT INTO usuario (login, senha, data_atualizacao, data_inclusao) VALUES (:login, :senha, :data_atualizacao, :data_inclusao)";
        return this.jdbcClient
                .sql(sql)
                .param("login", usuario.getLogin())
                .param("senha", usuario.getSenha())
                .param("data_atualizacao", LocalDateTime.now())
                .param("data_inclusao", LocalDateTime.now())
                .update();
    }

    @Override
    public Integer update(UsuarioRequestDTO usuario, Long id) {
        String sql = "UPDATE usuario SET login = :login, senha = :senha, data_atualizacao = :data_atualizacao WHERE id_usuario = :id";
        return this.jdbcClient
                .sql(sql)
                .param("login", usuario.getLogin())
                .param("senha", usuario.getSenha())
                .param("data_atualizacao", LocalDateTime.now())
                .param("id", id)
                .update();
    }

    @Override
    public Integer delete(Long id) {
        String sql = "DELETE FROM usuario WHERE id_usuario = :id";
        return this.jdbcClient
                .sql(sql)
                .param("id", id)
                .update();
    }

    @Override
    public Optional<Usuario> findByLogin(String login) {
        String sql = "SELECT a.* FROM usuario a WHERE a.login = :login";
        return this.jdbcClient
                .sql(sql)
                .param("login", login)
                .query(Usuario.class)
                .optional();
    }
}
