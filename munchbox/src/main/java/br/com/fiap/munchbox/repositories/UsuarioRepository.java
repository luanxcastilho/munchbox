package br.com.fiap.munchbox.repositories;

import br.com.fiap.munchbox.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>
{

    Usuario findByLogin(String login);

}
