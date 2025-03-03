package br.com.fiap.munchbox.repositories;

import br.com.fiap.munchbox.entities.RestauranteProduto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestauranteProdutoRepository extends JpaRepository<RestauranteProduto, Long>
{
}
