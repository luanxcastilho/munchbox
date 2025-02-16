package br.com.fiap.munchbox.repositories;

import br.com.fiap.munchbox.entities.Proprietario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProprietarioRepository extends JpaRepository<Proprietario, Long>
{

}
