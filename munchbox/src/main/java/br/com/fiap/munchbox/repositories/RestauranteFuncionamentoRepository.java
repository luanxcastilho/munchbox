package br.com.fiap.munchbox.repositories;

import br.com.fiap.munchbox.entities.RestauranteFuncionamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestauranteFuncionamentoRepository extends JpaRepository<RestauranteFuncionamento, Long>
{
}
