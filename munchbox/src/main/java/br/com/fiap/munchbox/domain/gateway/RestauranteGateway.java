package br.com.fiap.munchbox.domain.gateway;

import br.com.fiap.munchbox.domain.core.Restaurante;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface RestauranteGateway
{
    void create(Restaurante restaurante);

    void update(Restaurante restaurante);

    void delete(Long id);

    Optional<Restaurante> findById(Long id);

    List<Restaurante> findAll(Pageable pageable);
}
