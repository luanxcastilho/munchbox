package br.com.fiap.munchbox.domain.gateway;

import br.com.fiap.munchbox.domain.core.Cliente;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ClienteGateway
{
    void create(Cliente cliente);
    void update(Cliente cliente);
    void delete(Long id);
    Optional<Cliente> findById(Long id);
    List<Cliente> findAll(Pageable pageable);
}
