package br.com.fiap.munchbox.repositories;

import br.com.fiap.munchbox.dtos.clienteendereco.ClienteEnderecoRequestDTO;
import br.com.fiap.munchbox.entities.ClienteEndereco;

import java.util.List;
import java.util.Optional;

public interface ClienteEnderecoRepository {

    Optional<ClienteEndereco> findById(Long id);

    List<ClienteEndereco> findAll(int size, int offset);

    Integer create(ClienteEnderecoRequestDTO clienteEndereco);

    Integer update(ClienteEnderecoRequestDTO clienteEndereco, Long id);

    Integer delete(Long id);

}
