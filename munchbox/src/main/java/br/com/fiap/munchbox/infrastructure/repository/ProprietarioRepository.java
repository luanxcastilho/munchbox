package br.com.fiap.munchbox.infrastructure.repository;

import br.com.fiap.munchbox.domain.core.Proprietario;
import br.com.fiap.munchbox.domain.gateway.ProprietarioGateway;
import br.com.fiap.munchbox.infrastructure.mapper.ProprietarioMapper;
import br.com.fiap.munchbox.infrastructure.entity.ProprietarioEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class ProprietarioRepository implements ProprietarioGateway
{
    private final ProprietarioRepositoryJpa proprietarioRepositoryJpa;

    public ProprietarioRepository(ProprietarioRepositoryJpa proprietarioRepositoryJpa)
    {
        this.proprietarioRepositoryJpa = proprietarioRepositoryJpa;
    }

    @Override
    public void create(Proprietario proprietario)
    {
        ProprietarioEntity proprietarioEntity = ProprietarioMapper.toEntity(proprietario);
        proprietarioRepositoryJpa.save(proprietarioEntity);
    }

    @Override
    public void update(Proprietario proprietario)
    {
        ProprietarioEntity proprietarioEntity = ProprietarioMapper.toEntity(proprietario);
        proprietarioRepositoryJpa.save(proprietarioEntity);
    }

    @Override
    public void delete(Long id)
    {
        proprietarioRepositoryJpa.deleteById(id);
    }

    @Override
    public Optional<Proprietario> findById(Long id) {
        return proprietarioRepositoryJpa.findById(id).map(ProprietarioMapper::toDomain);
    }


    @Override
    public List<Proprietario> findAll(Pageable pageable)
    {
        List<ProprietarioEntity> proprietarioEntities = proprietarioRepositoryJpa.findAll(pageable).getContent();
        return proprietarioEntities.stream().map(ProprietarioMapper::toDomain).collect(Collectors.toList());
    }
}
