package br.com.fiap.munchbox.domain.gateway;

import br.com.fiap.munchbox.domain.core.ClienteEndereco;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ClienteEnderecoGateway
{
    void create(ClienteEndereco clienteEndereco);
    void update(ClienteEndereco clienteEndereco);
    void delete(Long id);
    Optional<ClienteEndereco> findById(Long id);
    List<ClienteEndereco> findAll(Pageable pageable);
}
