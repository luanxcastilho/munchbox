package br.com.fiap.munchbox.repositories;

import br.com.fiap.munchbox.entities.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestauranteRepository extends JpaRepository<Restaurante, Long>
{
}
