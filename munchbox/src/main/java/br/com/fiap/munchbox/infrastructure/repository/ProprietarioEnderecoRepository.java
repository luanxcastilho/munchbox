package br.com.fiap.munchbox.infrastructure.repository;

import br.com.fiap.munchbox.domain.core.ProprietarioEndereco;
import br.com.fiap.munchbox.domain.gateway.ProprietarioEnderecoGateway;
import br.com.fiap.munchbox.infrastructure.mapper.ProprietarioEnderecoMapper;
import br.com.fiap.munchbox.infrastructure.entity.ProprietarioEnderecoEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class ProprietarioEnderecoRepository implements ProprietarioEnderecoGateway
{
    private final ProprietarioEnderecoRepositoryJpa proprietarioEnderecoRepositoryJpa;

    public ProprietarioEnderecoRepository(ProprietarioEnderecoRepositoryJpa proprietarioEnderecoRepositoryJpa)
    {
        this.proprietarioEnderecoRepositoryJpa = proprietarioEnderecoRepositoryJpa;
    }

    @Override
    public void create(ProprietarioEndereco proprietarioEndereco)
    {
        ProprietarioEnderecoEntity proprietarioEnderecoEntity = ProprietarioEnderecoMapper.toEntity(proprietarioEndereco);
        proprietarioEnderecoRepositoryJpa.save(proprietarioEnderecoEntity);
    }

    @Override
    public void update(ProprietarioEndereco proprietarioEndereco)
    {
        ProprietarioEnderecoEntity proprietarioEnderecoEntity = ProprietarioEnderecoMapper.toEntity(proprietarioEndereco);
        proprietarioEnderecoRepositoryJpa.save(proprietarioEnderecoEntity);
    }

    @Override
    public void delete(Long id)
    {
        proprietarioEnderecoRepositoryJpa.deleteById(id);
    }

    @Override
    public Optional<ProprietarioEndereco> findById(Long id)
    {
        return proprietarioEnderecoRepositoryJpa.findById(id).map(ProprietarioEnderecoMapper::toDomain);
    }

    @Override
    public List<ProprietarioEndereco> findAll(Pageable pageable)
    {
        List<ProprietarioEnderecoEntity> proprietarioEntities = proprietarioEnderecoRepositoryJpa.findAll(pageable).getContent();
        return proprietarioEntities.stream().map(ProprietarioEnderecoMapper::toDomain).collect(Collectors.toList());
    }
}
