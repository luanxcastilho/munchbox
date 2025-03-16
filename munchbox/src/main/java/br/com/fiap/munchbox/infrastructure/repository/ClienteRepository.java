package br.com.fiap.munchbox.infrastructure.repository;

import br.com.fiap.munchbox.domain.core.Cliente;
import br.com.fiap.munchbox.domain.gateway.ClienteGateway;
import br.com.fiap.munchbox.infrastructure.mapper.ClienteMapper;
import br.com.fiap.munchbox.infrastructure.entity.ClienteEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class ClienteRepository implements ClienteGateway
{
    private final ClienteRepositoryJpa clienteRepositoryJpa;

    public ClienteRepository(ClienteRepositoryJpa clienteRepositoryJpa)
    {
        this.clienteRepositoryJpa = clienteRepositoryJpa;
    }

    @Override
    public void create(Cliente cliente)
    {
        ClienteEntity clienteEntity = ClienteMapper.toEntity(cliente);
        clienteRepositoryJpa.save(clienteEntity);
    }

    @Override
    public void update(Cliente cliente)
    {
        ClienteEntity clienteEntity = ClienteMapper.toEntity(cliente);
        clienteRepositoryJpa.save(clienteEntity);
    }

    @Override
    public void delete(Long id)
    {
        clienteRepositoryJpa.deleteById(id);
    }

    @Override
    public Optional<Cliente> findById(Long id) {
        return clienteRepositoryJpa.findById(id).map(ClienteMapper::toDomain);
    }


    @Override
    public List<Cliente> findAll(Pageable pageable)
    {
        List<ClienteEntity> clienteEntities = clienteRepositoryJpa.findAll(pageable).getContent();
        return clienteEntities.stream().map(ClienteMapper::toDomain).collect(Collectors.toList());
    }
}
