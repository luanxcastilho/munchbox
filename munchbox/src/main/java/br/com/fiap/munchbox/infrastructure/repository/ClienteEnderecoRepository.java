package br.com.fiap.munchbox.infrastructure.repository;

import br.com.fiap.munchbox.domain.core.ClienteEndereco;
import br.com.fiap.munchbox.domain.gateway.ClienteEnderecoGateway;
import br.com.fiap.munchbox.infrastructure.mapper.ClienteEnderecoMapper;
import br.com.fiap.munchbox.infrastructure.entity.ClienteEnderecoEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class ClienteEnderecoRepository implements ClienteEnderecoGateway
{
    private final ClienteEnderecoRepositoryJpa clienteEnderecoRepositoryJpa;

    public ClienteEnderecoRepository(ClienteEnderecoRepositoryJpa clienteEnderecoRepositoryJpa)
    {
        this.clienteEnderecoRepositoryJpa = clienteEnderecoRepositoryJpa;
    }

    @Override
    public void create(ClienteEndereco clienteEndereco)
    {
        ClienteEnderecoEntity clienteEnderecoEntity = ClienteEnderecoMapper.toEntity(clienteEndereco);
        clienteEnderecoRepositoryJpa.save(clienteEnderecoEntity);
    }

    @Override
    public void update(ClienteEndereco clienteEndereco)
    {
        ClienteEnderecoEntity clienteEnderecoEntity = ClienteEnderecoMapper.toEntity(clienteEndereco);
        clienteEnderecoRepositoryJpa.save(clienteEnderecoEntity);
    }

    @Override
    public void delete(Long id)
    {
        clienteEnderecoRepositoryJpa.deleteById(id);
    }

    @Override
    public Optional<ClienteEndereco> findById(Long id)
    {
        return clienteEnderecoRepositoryJpa.findById(id).map(ClienteEnderecoMapper::toDomain);
    }

    @Override
    public List<ClienteEndereco> findAll(Pageable pageable)
    {
        List<ClienteEnderecoEntity> clienteEntities = clienteEnderecoRepositoryJpa.findAll(pageable).getContent();
        return clienteEntities.stream().map(ClienteEnderecoMapper::toDomain).collect(Collectors.toList());
    }
}
