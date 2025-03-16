package br.com.fiap.munchbox.domain.gateway;

import br.com.fiap.munchbox.domain.core.ProprietarioEndereco;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ProprietarioEnderecoGateway
{
    void create(ProprietarioEndereco proprietarioEndereco);
    void update(ProprietarioEndereco proprietarioEndereco);
    void delete(Long id);
    Optional<ProprietarioEndereco> findById(Long id);
    List<ProprietarioEndereco> findAll(Pageable pageable);
}
