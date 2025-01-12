package br.com.fiap.munchbox.repositories;

import br.com.fiap.munchbox.dtos.cliente.ClienteRequestDTO;
import br.com.fiap.munchbox.entities.Cliente;

import java.util.List;
import java.util.Optional;

public interface ClienteRepository {

    Optional<Cliente> findById(Long id);

    List<Cliente> findAll(int size, int offset);

    Integer create(ClienteRequestDTO cliente);

    Integer update(ClienteRequestDTO cliente, Long id);

    Integer delete(Long id);
}
